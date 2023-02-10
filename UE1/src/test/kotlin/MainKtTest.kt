import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun generateANonRepeatingNumber1() {
        val toTest = generateANonRepeatingNumber(2)?.toList()?.distinct()
        if(toTest != null ){
            assertEquals(toTest.size,2)
        }else{
            fail<String>("failed because the function returned null")
        }
    }

    @Test
    fun generateANonRepeatingNumber2() {
        val toTest = generateANonRepeatingNumber(5)?.toList()?.distinct()
        if(toTest != null ){
            assertEquals(toTest.size,5)
        }else{
            fail<String>("failed because the function returned null")
        }
    }

    @Test
    fun generateANonRepeatingNumber3() {
        val toTest = generateANonRepeatingNumber(9)?.toList()?.distinct()
        if(toTest != null ){
            assertEquals(toTest.size,9)
        }else{
            fail<String>("failed because the function returned null")
        }
    }

    @Test
    fun generateANonRepeatingNumber4() {
        val toTest = generateANonRepeatingNumber(10)?.toList()?.distinct()
        if(toTest == null ){
            return
        }else{
            fail<String>("shouldn't operate over the range of 9")
        }
    }


    @Test
    fun checkInputString1() {
        val toTest: NMReturn = checkInputString("1234", "8576")
        assertEquals(toTest,NMReturn(0,0))
    }

    @Test
    fun checkInputString2() {
        val toTest: NMReturn = checkInputString("5678", "8576")
        assertEquals(toTest,NMReturn(4,1))
    }

    @Test
    fun checkInputString3() {
        val toTest: NMReturn = checkInputString("5555", "8576")
        assertEquals(toTest,NMReturn(1,1))
    }

    @Test
    fun checkInputString4() {
        val toTest: NMReturn = checkInputString("3586", "8576")
        assertEquals(toTest,NMReturn(3,2))
    }

    @Test
    fun checkInputString5() {
        val toTest: NMReturn = checkInputString("8576", "8576")
        assertEquals(toTest,NMReturn(4,4))
    }


}