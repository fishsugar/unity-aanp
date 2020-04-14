package unity_aanp.datamodel;

import javassist.CtField;
import javassist.Modifier;

public class FieldInfo {
    private final String signature;
    private final boolean isStatic;
    private final String name;
    private final TypeString type;

    public FieldInfo(String signature, boolean isStatic, String name, TypeString type) {
        this.signature = signature;
        this.isStatic = isStatic;
        this.name = name;
        this.type = type;
    }

    public static FieldInfo fromCtField(CtField field) {
        return new FieldInfo(
                field.getSignature(), Modifier.isStatic(field.getModifiers()),
                field.getName(), TypeString.fromDescriptor(field.getSignature()));
    }

    public String getName() {
        return name;
    }

    public TypeString getType() {
        return type;
    }

    public String getSignature() {
        return signature;
    }

    public boolean isStatic() {
        return isStatic;
    }
}
