
package br.novaanimations.core;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class NovaTransformer implements IClassTransformer {

    @Override
    public byte[] transform(
        String name,
        String transformedName,
        byte[] basicClass
    ) {

        if (basicClass == null) {
            return basicClass;
        }

        if (!"net.minecraft.client.model.ModelBiped"
                .equals(transformedName)) {
            return basicClass;
        }

        ClassNode classNode = new ClassNode();

        ClassReader reader = new ClassReader(basicClass);
        reader.accept(classNode, 0);

        boolean modified = false;

        for (MethodNode method : classNode.methods) {

            if (!method.desc.startsWith("(FFFFFFL")
                    || !method.desc.endsWith(";)V")) {
                continue;
            }

            boolean correctName =
                method.name.equals("setRotationAngles")
                || method.name.equals("func_78087_a")
                || method.name.equals("a");

            if (!correctName) {
                continue;
            }

            for (AbstractInsnNode instruction :
                    method.instructions.toArray()) {

                if (instruction.getOpcode() == Opcodes.RETURN) {

                    InsnList injection = new InsnList();

                    injection.add(
                        new VarInsnNode(Opcodes.ALOAD, 0)
                    );

                    injection.add(
                        new VarInsnNode(Opcodes.ALOAD, 7)
                    );

                    injection.add(
                        new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            "br/novaanimations/client/AnimationHooks",
                            "apply",
                            "(Lnet/minecraft/client/model/ModelBiped;" +
                            "Lnet/minecraft/entity/Entity;)V",
                            false
                        )
                    );

                    method.instructions.insertBefore(
                        instruction,
                        injection
                    );

                    modified = true;
                }
            }
        }

        if (!modified) {
            System.err.println(
                "[NovaAnimations] ModelBiped method not found!"
            );

            return basicClass;
        }

        ClassWriter writer = new ClassWriter(
            ClassWriter.COMPUTE_MAXS
        );

        classNode.accept(writer);

        return writer.toByteArray();
    }
}
