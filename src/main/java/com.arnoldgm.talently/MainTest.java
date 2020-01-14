package com.arnoldgm.talently;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainTest {

    @Test
    public void customCaseReturnValues() {
        Integer[] items = {3, 5, 2, 8, 11, 19, 1, -1, 6};
        Integer[] results = findPairs(items, 30);
        Assert.assertEquals(19, (int) results[0]);
        Assert.assertEquals(11, (int) results[1]);

    }


    @Test
    public void returnValues() {
        Integer[] items = {3, 5, -4, 8, 11, 1, -1, 6};
        Integer[] results = findPairs(items, 10);
        Assert.assertEquals(11, (int) results[0]);
        Assert.assertEquals(-1, (int) results[1]);

    }

    @Test
    public void notReturnValues() {
        Integer[] items2 = {2, -7, 4, 8, -11};
        Integer[] results2 = findPairs(items2, 56);
        Assert.assertNull(results2[0]);
        Assert.assertNull(results2[1]);
    }

    public Integer[] findPairs(Integer[] items, int target) {


        List<Integer> items2 = Arrays.stream(items)
                .sorted()
                .collect(Collectors.toList());

        Integer divider = target / 2;
        List<Integer> itemsCenter = items2.stream()
                .sorted()
                .filter(i -> i.equals(divider))
                .collect(Collectors.toList());

        if (itemsCenter.size() >= 2) return new Integer[]{divider, divider};

        List<Integer> itemsLeft = items2.stream()
                .sorted()
                .filter(i -> i < divider)
                .collect(Collectors.toList());

        List<Integer> itemsRigth = items2.stream()
                .sorted()
                .filter(i -> i >= divider)
                .collect(Collectors.toList());

        for (Integer item : itemsRigth) {
            Optional<Integer> searchedItem = itemsLeft.stream()
                    .filter(integer -> integer == target - item)
                    .findFirst();
            if (searchedItem.isPresent()) {
                return new Integer[]{item, searchedItem.get()};
            }

        }
        return new Integer[2];
    }
}
