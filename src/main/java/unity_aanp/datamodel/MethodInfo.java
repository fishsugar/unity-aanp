package unity_aanp.datamodel;

import javassist.CtMethod;
import javassist.Modifier;

public class MethodInfo {
    private final String signature;
    private final boolean isStatic;
    private final String name;
    private final TypeString returnType;
    private final TypeString[] paramTypes;

    public MethodInfo(String signature, boolean isStatic, String name, TypeString returnType, TypeString[] paramTypes) {
        this.signature = signature;
        this.isStatic = isStatic;
        this.name = name;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
    }

    public static MethodInfo fromCtMethod(CtMethod method) {
        MethodDescriptorInfo descriptorInfo = MethodDescriptorInfo.fromDescriptor(method.getSignature());
        return new MethodInfo(
                method.getSignature(), Modifier.isStatic(method.getModifiers()),
                method.getName(), descriptorInfo.returnType, descriptorInfo.paramTypes);
    }

    public String getName() {
        return name;
    }

    public TypeString getReturnType() {
        return returnType;
    }

    public TypeString[] getParamTypes() {
        return paramTypes;
    }

    public String getSignature() {
        return signature;
    }

    public boolean isStatic() {
        return isStatic;
    }
}
