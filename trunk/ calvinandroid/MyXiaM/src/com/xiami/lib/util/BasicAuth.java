package com.xiami.lib.util;

public class BasicAuth {

	private static byte[] cvtTable = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };

	  public static String encode(String paramString1, String paramString2)
	  {
	    String str = String.valueOf(paramString1);
	    byte[] arrayOfByte1 = (str + ":" + paramString2).getBytes();
	    byte[] arrayOfByte2 = new byte[(arrayOfByte1.length / 3 + 1) * 4];
	    int i = 0;
	    int j = 0;
	    int k = arrayOfByte1.length;
	    if (j >= k)
	      return new String(arrayOfByte2);
	    int m = arrayOfByte1.length - j;
	    if (m > 2)
	    {
	      int n = arrayOfByte1[j] << 16;
	      int i1 = j + 1;
	      int i2 = arrayOfByte1[i1] << 8;
	      int i3 = n | i2;
	      int i4 = j + 2;
	      int i5 = arrayOfByte1[i4];
	      int i6 = i3 | i5;
	      int i7 = i + 1;
	      byte[] arrayOfByte3 = cvtTable;
	      int i8 = (i6 & 0xFC0000) >> 18;
	      byte i9 = arrayOfByte3[i8];
	      arrayOfByte2[i] = i9;
	      int i10 = i7 + 1;
	      byte[] arrayOfByte4 = cvtTable;
	      int i11 = (i6 & 0x3F000) >> 12;
	      byte i12 = arrayOfByte4[i11];
	      arrayOfByte2[i7] = i12;
	      int i13 = i10 + 1;
	      byte[] arrayOfByte5 = cvtTable;
	      int i14 = (i6 & 0xFC0) >> 6;
	      byte i15 = arrayOfByte5[i14];
	      arrayOfByte2[i10] = i15;
	      i = i13 + 1;
	      byte[] arrayOfByte6 = cvtTable;
	      int i16 = i6 & 0x3F;
	      byte i17 = arrayOfByte6[i16];
	      arrayOfByte2[i13] = i17;
	    }
	    else
	    {
	      j += 3;
	      if (m == 2)
	      {
	        int i18 = arrayOfByte1[j] << 16;
	        int i19 = j + 1;
	        int i20 = arrayOfByte1[i19] << 8;
	        int i21 = i18 | i20;
	        int i22 = i + 1;
	        byte[] arrayOfByte7 = cvtTable;
	        int i23 = (i21 & 0xFC0000) >> 18;
	        byte i24 = arrayOfByte7[i23];
	        arrayOfByte2[i] = i24;
	        int i25 = i22 + 1;
	        byte[] arrayOfByte8 = cvtTable;
	        int i26 = (i21 & 0x3F000) >> 12;
	        byte i27 = arrayOfByte8[i26];
	        arrayOfByte2[i22] = i27;
	        int i28 = i25 + 1;
	        byte[] arrayOfByte9 = cvtTable;
	        int i29 = (i21 & 0xFC0) >> 6;
	        byte i30 = arrayOfByte9[i29];
	        arrayOfByte2[i25] = i30;
	        i = i28 + 1;
	        arrayOfByte2[i28] = 61;
	      }
	      int i31 = arrayOfByte1[j] << 16;
	      int i32 = i + 1;
	      byte[] arrayOfByte10 = cvtTable;
	      int i33 = (i31 & 0xFC0000) >> 18;
	      byte i34 = arrayOfByte10[i33];
	      arrayOfByte2[i] = i34;
	      int i35 = i32 + 1;
	      byte[] arrayOfByte11 = cvtTable;
	      int i36 = (i31 & 0x3F000) >> 12;
	      byte i37 = arrayOfByte11[i36];
	      arrayOfByte2[i32] = i37;
	      int i38 = i35 + 1;
	      arrayOfByte2[i35] = 61;
	      i = i38 + 1;
	      arrayOfByte2[i38] = 61;
	    }
	    return new String(arrayOfByte2);
	  }
}
