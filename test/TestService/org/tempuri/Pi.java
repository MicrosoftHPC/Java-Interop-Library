//-----------------------------------------------------------------------
// <copyright file="Utility.java" company="Microsoft">
//     Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// <summary>
//    Utility class for EchoSvc
// </summary>
// <author>Jiabin Hu</author>
//-----------------------------------------------------------------------

package org.tempuri;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pi
{
    public static int mul_mod(int a, int b, int m)
    {
        return (int)(((long) a * (long) b) % m);
    }
    
    /* return the inverse of x mod y */
    private static int inv_mod(int x, int y)
    {
        int q, u, v, a, c, t;

        u = x;
        v = y;
        c = 1;
        a = 0;

        do
        {
            q = v / u;

            t = c;
            c = a - q * c;
            a = t;

            t = u;
            u = v - q * u;
            v = t;
        } while (u != 0);

        a = a % y;

        if (a < 0)
        {
            a = y + a;
        }

        return a;
    }
    
    /* return (a^b) mod m */
    private static int pow_mod(int a, int b, int m)
    {
        int r, aa;

        r = 1;
        aa = a;

        while (true)
        {
            if ((b & 1) != 0)
            {
                r = mul_mod(r, aa, m);
            }

            b = b >> 1;

            if (b == 0)
            {
                break;
            }

            aa = mul_mod(aa, aa, m);
        }

        return r;
    }
    
    /* return true if n is prime */
    private static boolean is_prime(int n)
    {

        if (n < 2) // negative numbers, 0, and 1 are not prime
        {
            return false;
        }

        if (n == 2) // 2 is prime
        {
            return true;
        }

        if ((n % 2) == 0)
        {
            return false;
        }

        int r = (int)Math.sqrt(n);

        for (int i = 3; i <= r; i += 2)
        {
            if ((n % i) == 0)
            {
                return false;
            }
        }

        return true;
    }
    
    /* return the prime number immediatly after n */
    private static int next_prime(int n)
    {
        do
        {
            n++;
        } while (!is_prime(n));

        return n;
    }
    
    private static int calculatePiDigits(int n)
    {
        int av, vmax, num, den, s, t;

        int N = (int)((n + 20) * Math.log(10) / Math.log(2));

        double sum = 0;

        for (int a = 3; a <= (2 * N); a = next_prime(a))
        {
            vmax = (int)(Math.log(2 * N) / Math.log(a));

            av = 1;

            for (int i = 0; i < vmax; i++)
            {
                av = av * a;
            }

            s = 0;
            num = 1;
            den = 1;
            int v = 0;
            int kq = 1;
            int kq2 = 1;

            for (int k = 1; k <= N; k++)
            {

                t = k;

                if (kq >= a)
                {
                    do
                    {
                        t = t / a;
                        v--;
                    } while ((t % a) == 0);

                    kq = 0;
                }
                kq++;
                num = mul_mod(num, t, av);

                t = 2 * k - 1;

                if (kq2 >= a)
                {
                    if (kq2 == a)
                    {
                        do
                        {
                            t = t / a;
                            v++;
                        } while ((t % a) == 0);
                    }
                    kq2 -= a;
                }
                den = mul_mod(den, t, av);
                kq2 += 2;

                if (v > 0)
                {
                    t = inv_mod(den, av);
                    t = mul_mod(t, num, av);
                    t = mul_mod(t, k, av);

                    for (int i = v; i < vmax; i++)
                    {
                        t = mul_mod(t, a, av);
                    }

                    s += t;

                    if (s >= av)
                    {
                        s -= av;
                    }
                }

            }

            t = pow_mod(10, n - 1, av);
            s = mul_mod(s, t, av);
            sum = (sum + (double)s / (double)av) % 1.0;
        }

        return (int)(sum * 1e9);
    }
    
    public static String calculatePi(int digits)
    {
        String result = "";

        if (digits > 0)
        {
            for (int i = 0; i < digits; i += 9)
            {
                result += String.valueOf(calculatePiDigits(i + 1));
            }
        }

        return result;
    }

    public int echo()
    {
        return 1;
    }
    
    public static String calculatePi(long timeInMs)
    {
        String result = "";

        GregorianCalendar end = new GregorianCalendar();
        end.add(GregorianCalendar.MILLISECOND, (int) timeInMs);
        for (int i = 0; end.after(new GregorianCalendar()); i += 9) // step as 9
        {
            result += String.valueOf(calculatePiDigits(i + 1));
        }

        return result;
    }

    public static int Echo()
    {
        return 1;
    }
}
