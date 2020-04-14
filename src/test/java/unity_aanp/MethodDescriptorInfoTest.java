package unity_aanp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import unity_aanp.datamodel.MethodDescriptorInfo;
import unity_aanp.datamodel.TypeString;

import java.util.Arrays;

public class MethodDescriptorInfoTest {
    @Test
    public void testMethodDescriptorInfo() {
        MethodDescriptorInfo desc =  MethodDescriptorInfo.fromDescriptor("(I[I[Ljava/lang/String;)V");
        assertArrayEquals(
                new String[]{"int", "int[]", "string[]"},
                Arrays.stream(desc.paramTypes).map(TypeString::getCsharpName).toArray());
        assertEquals("void", desc.returnType.getCsharpName());
    }
}