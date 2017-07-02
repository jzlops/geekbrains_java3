package ru.tikhonov.term6;

/**
 * Edit by Tikhonov Sergey
 */
class ArrayOps {
    int[] haul(int[] array) throws RuntimeException {
        final int CONTROL_VALUE = 4;
        int[] result = null;
        if (array != null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == CONTROL_VALUE) {
                    result = new int[array.length - i - 1];
                    for (int j = 0; j < result.length; j++) {
                        result[j] = array[i + j + 1];
                    }
                    if (i == array.length - 1) {
                        result = null;
                    }
                    return result;
                }
            }
            throw new RuntimeException("Element 4 not found");
        }
        return result;
    }


    boolean check(int[] array) {
        final int CONTROL_VALUE1 = 4;
        final int CONTROL_VALUE2 = 1;
        boolean result;
        boolean isV1 = false;
        boolean isV2 = false;
        boolean isVX = false;
        for (int anArrayA : array) {
            if (anArrayA == CONTROL_VALUE1) {
                isV1 = true;
                continue;
            }
            if (anArrayA == CONTROL_VALUE2) {
                isV2 = true;
                continue;
            }
            isVX = true;
        }
        result = (!isVX) && (isV2) && (isV1);
        return result;
    }
}
