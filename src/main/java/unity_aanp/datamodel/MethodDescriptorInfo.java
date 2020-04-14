package unity_aanp.datamodel;

import javassist.bytecode.Descriptor;

public class MethodDescriptorInfo {
    public TypeString[] paramTypes;
    public TypeString returnType;

    public MethodDescriptorInfo(TypeString[] paramTypes, TypeString returnType) {
        this.paramTypes = paramTypes;
        this.returnType = returnType;
    }

    public static MethodDescriptorInfo fromDescriptor(String desc) {
        TypeString[] paramTypes = new TypeString[Descriptor.numOfParameters(desc)];

        String paramDesc = desc.substring(1, desc.lastIndexOf(')'));
        TypeString returnType = TypeString.fromDescriptor(desc.substring(desc.lastIndexOf(')') + 1));

        int pos = 0;
        for (int i = 0; i < paramTypes.length; i++) {
            int posEnd = pos;

            while (true) {
                char ch = paramDesc.charAt(posEnd);
                if (ch == '[') {
                    posEnd++;
                    continue;
                } else if (ch == 'L') {
                    posEnd = paramDesc.indexOf(';', posEnd);
                    break;
                } else {
                    break;
                }
            }

            paramTypes[i] = TypeString.fromDescriptor(paramDesc.substring(pos, posEnd + 1));
            pos = posEnd + 1;
        }

        return new MethodDescriptorInfo(paramTypes, returnType);
    }
}