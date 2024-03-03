package org.projectt.webservices;

import org.springframework.stereotype.Service;

@Service
public class NumberServiceImp implements  NumberService{

    private static final String[] UNITS = {"", "nghìn", "triệu", "tỷ"};
    private static final String[] NUMBERS = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] TENS = {"", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};


    @Override
    public String convertNumberToWords(int number) {
        if (number == 0) {
            return "zero";
        } else {
            return convertToVietnamese(number);
        }
    }

    public static String convertToVietnamese(int number) {
        if (number == 0) {
            return "không";
        }

        String result = "";
        int unitIndex = 0;

        while (number > 0) {
            int chunk = number % 1000;
            if (chunk > 0) {
                result = convertChunkToVietnamese(chunk) + " " + UNITS[unitIndex] + " " + result;
            }
            unitIndex++;
            number /= 1000;
        }

        return result.trim();
    }

    private static String convertChunkToVietnamese(int chunk) {
        String result = "";

        int hundreds = chunk / 100;
        int tensAndOnes = chunk % 100;

        if (hundreds > 0) {
            result += NUMBERS[hundreds] + " trăm ";
        }

        if (tensAndOnes > 0) {
            if (tensAndOnes < 10) {
                result += NUMBERS[tensAndOnes];
            } else if (tensAndOnes < 20) {
                result += "mười " + NUMBERS[tensAndOnes % 10];
            } else {
                result += TENS[tensAndOnes / 10] + " " + NUMBERS[tensAndOnes % 10];
            }
        }

        return result;
    }
}
