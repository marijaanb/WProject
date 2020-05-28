import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

    public class Wall {

        private static int w = 32;
        private static int h = 10;

        private static void findGapPos(Stack<Integer> gaps, int pos, List<int[]> finalList) {
            if (pos < 0)
                System.out.println("Klaida1");
            else if (pos < w) {
                for (int i = 2; i <= 3; i++) {
                    gaps.push(pos + i);
                    findGapPos(gaps, pos + i, finalList);
                    gaps.pop();
                }
            } else if (pos == w) {
                int[] temp = new int[gaps.size() - 1];
                for (int i = 0; i < temp.length; i++)
                    temp[i] = gaps.get(i);
                finalList.add(temp);
            } else if (pos > w) {
                return;
            }
        }

        private static boolean commonElementsCheck(int[] a, int[] b) {
            for (int i = 0, j = 0; i < a.length && j < b.length; ) {
                if (a[i] == b[j])
                    return false;
                else if (a[i] < b[j])
                    i++;
                else if (a[i] > b[j])
                    j++;
                else
                    System.out.println("Klaida2");
            }
            return true;
        }


        public BigInteger computeSum() {
            List<int[]> gapPos = new ArrayList<>();
            findGapPos(new Stack<Integer>(), 0, gapPos);

            BigInteger[] howManyWays = new BigInteger[gapPos.size()];
            Arrays.fill(howManyWays, BigInteger.ONE);

            for (int i = 1; i < h; i++) {
                BigInteger[] newWays = new BigInteger[howManyWays.length];
                for (int j = 0; j < newWays.length; j++) {
                    BigInteger sum = BigInteger.ZERO;
                    for (int k = 0; k < howManyWays.length; k++) {
                        if (commonElementsCheck(gapPos.get(j), gapPos.get(k)))
                            sum = sum.add(howManyWays[k]);
                    }
                    newWays[j] = sum;
                }
                howManyWays = newWays;
            }

            BigInteger sum = BigInteger.ZERO;
            for (BigInteger x : howManyWays)
                sum = sum.add(x);
            return sum;
        }

        public static void main(String[] args) {
            System.out.println(new Wall().computeSum());
        }

    }

