public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder=new StringBuilder("\n345 \n56\n");
        System.out.println(stringBuilder.toString().trim());

      //  System.out.println(Integer.toBinaryString(Short.MIN_VALUE));
    }
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
