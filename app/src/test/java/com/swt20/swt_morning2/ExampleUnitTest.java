package com.swt20.swt_morning2;

import java.util.List;

import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addition_isMocked() {
        List<Integer> list = mock(List.class);
        when(list.get(Matchers.eq(123))).thenReturn(2);
        assertEquals(4, 2 + list.get(123));
    }
}