package unity_aanp.datamodel;

import javassist.CtClass;
import javassist.Modifier;

import java.util.Arrays;

public class ClassInfo {
    public PackageString getPackageName() {
        return packageName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public MethodInfo[] getMethods() {
        return methods;
    }

    public FieldInfo[] getFields() {
        return fields;
    }

    private final PackageString packageName;
    private final String simpleName;
    private final MethodInfo[] methods;
    private final FieldInfo[] fields;

    public ClassInfo(PackageString packageName, String simpleName, MethodInfo[] methods, FieldInfo[] fields) {
        this.packageName = packageName;
        this.simpleName = simpleName;
        this.methods = methods;
        this.fields = fields;
    }

    public static ClassInfo fromCtClass(CtClass clazz) {
        var methods = Arrays.stream(clazz.getMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .map(MethodInfo::fromCtMethod).toArray(MethodInfo[]::new);
        var fields = Arrays.stream(clazz.getFields())
                .filter(f -> Modifier.isPublic(f.getModifiers()))
                .map(FieldInfo::fromCtField).toArray(FieldInfo[]::new);

        return new ClassInfo(
                PackageString.fromJavaName(clazz.getPackageName()),
                clazz.getSimpleName(), methods, fields);
    }
}
