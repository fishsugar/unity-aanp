package unity_aanp.datamodel;

public class PackageString {
    private final String javaName;

    private PackageString(String javaName) {
        this.javaName = javaName;
    }

    public static PackageString fromJavaName(String javaName) {
        return new PackageString(javaName);
    }

    public String getCsharpName(){
        return javaName.replaceAll("\\bbase\\b", "base_");
    }

    public String getJavaName() {
        return javaName;
    }
}
