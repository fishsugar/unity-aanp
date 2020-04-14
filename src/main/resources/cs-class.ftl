<#if packageName??>
namespace ${packageName.csharpName}
{
</#if>
<#assign fullName=packageName.csharpName+"."+simpleName>
public class ${simpleName}
{
    private static UnityEngine.AndroidJavaClass _javaClass;
    private UnityEngine.AndroidJavaObject _javaObject;

    static SimpleClass()
    {
        _javaClass = new UnityEngine.AndroidJavaClass("${fullName}");
    }

    public SimpleClass()
    {
        _javaObject = new UnityEngine.AndroidJavaObject("${fullName}");
    }
<#list fields as field>

    // ${field.signature}
    public <#if field.static>static </#if>${field.type.csharpName} ${field.name}
    {
        get { return <#if field.static>_javaClass.GetStatic<#else>_javaObject.Get</#if><${field.type.csharpName}>("${field.name}"); }
    }
</#list>
<#list methods as method>

    // ${method.signature}
    public <#if method.static>static </#if>${method.returnType.csharpName} ${method.name}(<#rt>
<#list method.paramTypes as paramType>
        ${paramType.csharpName} param${paramType?index}<#sep>, <#t>
</#list>)
    {
        <#if method.returnType.csharpName != "void">return </#if><#if method.static>_javaClass.CallStatic<#else>_javaObject.Call</#if><#if method.returnType.csharpName != "void"><${method.returnType.csharpName}></#if>(<#rt>
        "${method.name}", <#t>
        new object[]{<#t>
<#list method.paramTypes as paramType>
param${paramType?index}<#sep>, <#rt>
</#list>
<#t><#t>
        });<#lt>
    }
</#list>
}
<#if packageName??>}</#if>
