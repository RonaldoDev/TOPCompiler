/* Generated By:JavaCC: Do not edit this line. FunTokenManager.java */
package parser;
import java.io.*;

/** Token Manager. */
public class FunTokenManager implements FunConstants
{
  int countLexError = 0;

  public int foundLexError()
  {
    return countLexError;
  }

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x2008000000L) != 0L)
         {
            jjmatchedKind = 55;
            return 10;
         }
         if ((active0 & 0x280000000000L) != 0L)
            return 18;
         if ((active0 & 0x1000dff7fc0000L) != 0L)
         {
            jjmatchedKind = 55;
            return 17;
         }
         return -1;
      case 1:
         if ((active0 & 0x1000ffdfec0000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 1;
            return 17;
         }
         if ((active0 & 0x20100000L) != 0L)
            return 17;
         return -1;
      case 2:
         if ((active0 & 0xc82c0000L) != 0L)
            return 17;
         if ((active0 & 0x1000ff17c00000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 2;
            return 17;
         }
         return -1;
      case 3:
         if ((active0 & 0x7d15c00000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 3;
            return 17;
         }
         if ((active0 & 0x10008202000000L) != 0L)
            return 17;
         return -1;
      case 4:
         if ((active0 & 0x4c05000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 4;
            return 17;
         }
         if ((active0 & 0x3110c00000L) != 0L)
            return 17;
         return -1;
      case 5:
         if ((active0 & 0xc00000000L) != 0L)
            return 17;
         if ((active0 & 0x4005000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 5;
            return 17;
         }
         return -1;
      case 6:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 6;
            return 17;
         }
         if ((active0 & 0x4004000000L) != 0L)
            return 17;
         return -1;
      case 7:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 7;
            return 17;
         }
         return -1;
      case 8:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 8;
            return 17;
         }
         return -1;
      case 9:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 55;
            jjmatchedPos = 9;
            return 17;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 37:
         return jjStopAtPos(0, 17);
      case 40:
         return jjStopAtPos(0, 40);
      case 41:
         return jjStopAtPos(0, 41);
      case 42:
         return jjStopAtPos(0, 8);
      case 43:
         return jjStopAtPos(0, 6);
      case 44:
         return jjStopAtPos(0, 47);
      case 45:
         return jjStopAtPos(0, 7);
      case 46:
         return jjStopAtPos(0, 48);
      case 47:
         jjmatchedKind = 9;
         return jjMoveStringLiteralDfa1_0(0x2400000000000000L);
      case 59:
         return jjStopAtPos(0, 46);
      case 60:
         jjmatchedKind = 12;
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 61:
         jjmatchedKind = 10;
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 62:
         jjmatchedKind = 11;
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 91:
         return jjStopAtPos(0, 44);
      case 93:
         return jjStartNfaWithStates_0(0, 45, 18);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x4000400000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x8001800000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x6000000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x2008000000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x60000000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x10000080040000L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x100000000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x600000000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x1800000000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x10000000L);
      case 120:
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 123:
         return jjStopAtPos(0, 42);
      case 125:
         return jjStartNfaWithStates_0(0, 43, 18);
      default :
         return jjMoveNfa_0(3, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x400000000000000L) != 0L)
            return jjStopAtPos(1, 58);
         break;
      case 47:
         if ((active0 & 0x2000000000000000L) != 0L)
            return jjStopAtPos(1, 61);
         break;
      case 61:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(1, 13);
         else if ((active0 & 0x4000L) != 0L)
            return jjStopAtPos(1, 14);
         else if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(1, 15);
         else if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(1, 16);
         break;
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x680000000L);
      case 102:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(1, 29, 17);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x8010000000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x2002800000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x40080000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x4009240000L);
      case 114:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(1, 20, 17);
         return jjMoveStringLiteralDfa2_0(active0, 0x100400000L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x10001000000000L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x8200800000L);
      case 100:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(2, 19, 17);
         break;
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x110000000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000000000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x6000000000L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L);
      case 114:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(2, 21, 17);
         else if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(2, 27, 17);
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000L);
      case 116:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(2, 18, 17);
         else if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(2, 30, 17);
         return jjMoveStringLiteralDfa3_0(active0, 0x404000000L);
      case 119:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(2, 31, 17);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000400000L);
      case 100:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(3, 33, 17);
         break;
      case 101:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(3, 25, 17);
         return jjMoveStringLiteralDfa4_0(active0, 0x1004000000L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L);
      case 108:
         if ((active0 & 0x10000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 52, 17);
         return jjMoveStringLiteralDfa4_0(active0, 0x4010000000L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000000L);
      case 114:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 39, 17);
         break;
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x1800000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(4, 28, 17);
         return jjMoveStringLiteralDfa5_0(active0, 0x4000000000L);
      case 107:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(4, 22, 17);
         break;
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x804000000L);
      case 114:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 36, 17);
         return jjMoveStringLiteralDfa5_0(active0, 0x400000000L);
      case 115:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(4, 23, 17);
         break;
      case 116:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(4, 32, 17);
         else if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 37, 17);
         return jjMoveStringLiteralDfa5_0(active0, 0x1000000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000000L);
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000L);
      case 103:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(5, 35, 17);
         break;
      case 110:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(5, 34, 17);
         break;
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 38, 17);
         break;
      case 115:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(6, 26, 17);
         break;
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x1000000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x1000000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 116:
         return jjMoveStringLiteralDfa9_0(active0, 0x1000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x1000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 114:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(10, 24, 17);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 45;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x84000058ffffc9ffL & l) != 0L)
                  {
                     if (kind > 64)
                        kind = 64;
                     jjCheckNAdd(18);
                  }
                  else if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 49)
                        kind = 49;
                     jjCheckNAddStates(0, 2);
                  }
                  else if (curChar == 39)
                     jjCheckNAddStates(3, 9);
                  else if (curChar == 34)
                     jjCheckNAddStates(10, 13);
                  break;
               case 10:
               case 17:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 55)
                     kind = 55;
                  jjCheckNAdd(17);
                  break;
               case 18:
                  if ((0x84000058ffffc9ffL & l) == 0L)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAdd(18);
                  break;
               case 19:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 49)
                     kind = 49;
                  jjCheckNAddStates(0, 2);
                  break;
               case 20:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 49)
                     kind = 49;
                  jjCheckNAdd(20);
                  break;
               case 21:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(21, 22);
                  break;
               case 22:
                  if (curChar == 46)
                     jjCheckNAdd(23);
                  break;
               case 23:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 50)
                     kind = 50;
                  jjCheckNAdd(23);
                  break;
               case 24:
                  if (curChar == 34)
                     jjCheckNAddStates(10, 13);
                  break;
               case 25:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(25, 26);
                  break;
               case 26:
                  if (curChar == 34 && kind > 51)
                     kind = 51;
                  break;
               case 27:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(27, 28);
                  break;
               case 28:
                  if ((0x2400L & l) != 0L && kind > 65)
                     kind = 65;
                  break;
               case 29:
                  if (curChar == 39)
                     jjCheckNAddStates(3, 9);
                  break;
               case 30:
                  jjCheckNAdd(31);
                  break;
               case 31:
                  if (curChar == 39 && kind > 53)
                     kind = 53;
                  break;
               case 32:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(32, 33);
                  break;
               case 33:
                  if ((0x2400L & l) != 0L && kind > 66)
                     kind = 66;
                  break;
               case 34:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAdd(35);
                  break;
               case 35:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(35, 36);
                  break;
               case 36:
                  if (curChar == 39 && kind > 66)
                     kind = 66;
                  break;
               case 42:
                  if (curChar == 34)
                     jjCheckNAdd(31);
                  break;
               case 43:
                  if (curChar == 39)
                     jjCheckNAdd(31);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 55)
                        kind = 55;
                     jjCheckNAdd(17);
                  }
                  else if ((0xf0000001f0000001L & l) != 0L)
                  {
                     if (kind > 64)
                        kind = 64;
                     jjCheckNAdd(18);
                  }
                  if (curChar == 70)
                     jjstateSet[jjnewStateCnt++] = 14;
                  else if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 10;
                  else if (curChar == 84)
                     jjstateSet[jjnewStateCnt++] = 6;
                  else if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 10:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 55)
                        kind = 55;
                     jjCheckNAdd(17);
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 0:
                  if (curChar == 101 && kind > 54)
                     kind = 54;
                  break;
               case 1:
                  if (curChar == 117)
                     jjCheckNAdd(0);
                  break;
               case 2:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 4:
                  if (curChar == 69 && kind > 54)
                     kind = 54;
                  break;
               case 5:
                  if (curChar == 85)
                     jjCheckNAdd(4);
                  break;
               case 6:
                  if (curChar == 82)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if (curChar == 84)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 8:
                  if (curChar == 115)
                     jjCheckNAdd(0);
                  break;
               case 9:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 11:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 12:
                  if (curChar == 83)
                     jjCheckNAdd(4);
                  break;
               case 13:
                  if (curChar == 76)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 65)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 70)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 16:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 55)
                     kind = 55;
                  jjCheckNAdd(17);
                  break;
               case 17:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 55)
                     kind = 55;
                  jjCheckNAdd(17);
                  break;
               case 18:
                  if ((0xf0000001f0000001L & l) == 0L)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAdd(18);
                  break;
               case 25:
                  jjAddStates(14, 15);
                  break;
               case 27:
                  jjAddStates(16, 17);
                  break;
               case 30:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAdd(31);
                  break;
               case 32:
                  jjAddStates(18, 19);
                  break;
               case 34:
                  jjCheckNAdd(35);
                  break;
               case 35:
                  jjCheckNAddTwoStates(35, 36);
                  break;
               case 37:
                  if (curChar == 92)
                     jjCheckNAdd(36);
                  break;
               case 38:
                  if (curChar == 92)
                     jjAddStates(20, 25);
                  break;
               case 39:
                  if (curChar == 110)
                     jjCheckNAdd(31);
                  break;
               case 40:
                  if (curChar == 114)
                     jjCheckNAdd(31);
                  break;
               case 41:
                  if (curChar == 116)
                     jjCheckNAdd(31);
                  break;
               case 44:
                  if (curChar == 92)
                     jjCheckNAdd(31);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
               case 18:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 64)
                     kind = 64;
                  jjCheckNAdd(18);
                  break;
               case 25:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(14, 15);
                  break;
               case 27:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(16, 17);
                  break;
               case 30:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 32:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(18, 19);
                  break;
               case 34:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAdd(35);
                  break;
               case 35:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(35, 36);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 45 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_2()
{
   return jjMoveNfa_2(0, 0);
}
private int jjMoveNfa_2(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 1;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x2400L & l) != 0L)
                     kind = 62;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 1 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_1(0x800000000000000L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x800000000000000L) != 0L)
            return jjStopAtPos(1, 59);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
   20, 21, 22, 30, 32, 33, 34, 36, 37, 38, 25, 26, 27, 28, 25, 26, 
   27, 28, 32, 33, 39, 40, 41, 42, 43, 44, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, "\53", "\55", "\52", "\57", "\75", "\76", 
"\74", "\75\75", "\74\75", "\76\75", "\41\75", "\45", "\156\157\164", "\141\156\144", 
"\157\162", "\170\157\162", "\142\162\145\141\153", "\143\154\141\163\163", 
"\143\157\156\163\164\162\165\143\164\157\162", "\145\154\163\145", "\145\170\164\145\156\144\163", "\146\157\162", 
"\167\150\151\154\145", "\151\146", "\151\156\164", "\156\145\167", "\160\162\151\156\164", 
"\162\145\141\144", "\162\145\164\165\162\156", "\163\164\162\151\156\147", 
"\163\165\160\145\162", "\146\154\157\141\164", "\142\157\157\154\145\141\156", "\143\150\141\162", 
"\50", "\51", "\173", "\175", "\133", "\135", "\73", "\54", "\56", null, null, null, 
"\156\165\154\154", null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "multilinecomment",
   "singlelinecomment",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, 1, 0, -1, 2, 0, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0xffffffffffffc1L, 0x0L, 
};
static final long[] jjtoSkip = {
   0xfc0000000000003eL, 0x7L, 
};
static final long[] jjtoSpecial = {
   0x0L, 0x7L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[45];
private final int[] jjstateSet = new int[90];
private final StringBuilder jjimage = new StringBuilder();
private StringBuilder image = jjimage;
private int jjimageLen;
private int lengthOfMatch;
protected char curChar;
/** Constructor. */
public FunTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public FunTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 45; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 3 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   switch(curLexState)
   {
     case 0:
       try { input_stream.backup(0);
          while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
             curChar = input_stream.BeginToken();
       }
       catch (java.io.IOException e1) { continue EOFLoop; }
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       if (jjmatchedPos == 0 && jjmatchedKind > 60)
       {
          jjmatchedKind = 60;
       }
       break;
     case 2:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_2();
       if (jjmatchedPos == 0 && jjmatchedKind > 63)
       {
          jjmatchedKind = 63;
       }
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
           matchedToken.specialToken = specialToken;
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
           if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
           {
              matchedToken = jjFillToken();
              if (specialToken == null)
                 specialToken = matchedToken;
              else
              {
                 matchedToken.specialToken = specialToken;
                 specialToken = (specialToken.next = matchedToken);
              }
              SkipLexicalActions(matchedToken);
           }
           else
              SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      case 64 :
         image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    System.err.println("Linha " + input_stream.getEndLine() + " - Elemento invalido encontrado: " + image);
    countLexError++;
         break;
      case 65 :
         image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    System.err.println("Linha " + input_stream.getEndLine() + " - A string possui uma quebra de linha inesperada: " + image);
    countLexError++;
         break;
      case 66 :
         image.append(input_stream.GetSuffix(jjimageLen + (lengthOfMatch = jjmatchedPos + 1)));
    System.err.println("Linha " + input_stream.getEndLine() + " - Char invalido: " + image);
    countLexError++;
         break;
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
