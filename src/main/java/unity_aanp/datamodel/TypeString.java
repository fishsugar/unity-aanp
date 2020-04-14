package unity_aanp.datamodel;

import javassist.bytecode.Descriptor;

public class TypeString {
    private final String descriptor;

    private TypeString(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public String getCsharpName() {
        String javaName = Descriptor.toClassName(descriptor);
        int arraySuffixIndex = javaName.indexOf('[');
        String baseType;
        String arraySuffix;
        if (arraySuffixIndex != -1) {
            baseType = javaName.substring(0, arraySuffixIndex);
            arraySuffix = javaName.substring(arraySuffixIndex);
        } else {
            baseType = javaName;
            arraySuffix = "";
        }

        String csharpBaseType = switch (baseType) {
            case "java.lang.String" -> "string";
            case "boolean" -> "bool";
            case "java.lang.Object",
                    "android.os.Bundle",
                    "android.content.Context",
                    "android.app.Activity",
                    "android.widget.ImageView",
                    "android.os.Messenger" -> "UnityEngine.AndroidJavaObject";
            case "java.util.List" -> "UnityEngine.AndroidJavaObject"; //todo
            default -> baseType;
        };

        return csharpBaseType + arraySuffix;
    }

    public static TypeString fromDescriptor(String descriptor) {
        return new TypeString(descriptor);
    }
}
