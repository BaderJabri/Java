

import java.io.*;
import java.util.*;


public class Main {
  
  private static void mergeSort(int[] inputArray, String[] cards) {
    if (inputArray.length <= 1) {
  return;
}

  int inputLength = inputArray.length;

  int midIndex = inputLength / 2;
  int[] leftHalf = new int[midIndex];
  int[] rightHalf = new int[inputLength - midIndex];
  String[] leftHalfCards = new String[midIndex];
  String[] rightHalfCards = new String[inputLength - midIndex];

  for (int i = 0; i < midIndex; i++) {
    leftHalf[i] = inputArray[i];
    leftHalfCards[i] = cards[i];
  }

  for (int i = midIndex; i < inputLength; i++) {
    rightHalf[i - midIndex] = inputArray[i];
    rightHalfCards[i - midIndex] = cards[i];
  }

  mergeSort(leftHalf, leftHalfCards);
  mergeSort(rightHalf, rightHalfCards);

  merge(inputArray, leftHalf, rightHalf, leftHalfCards, rightHalfCards, cards);
}
  private static void merge (int[] inputArray, int[] leftHalf, int[] rightHalf, String[] leftHalfCards, String[] rightHalfCards, String[] cards) {
    // PRECONDITION: leftHalf[] and rightHalf[] are already sorted
    int leftSize = leftHalf.length;
    int rightSize = rightHalf.length;

    int i = 0, j = 0, k = 0;

    while (i < leftSize && j < rightSize) {
      if (leftHalf[i] <= rightHalf[j]) {
        inputArray[k] = leftHalf[i];
        cards[k] = leftHalfCards[i];
        i++;
      }
      else {
        inputArray[k] = rightHalf[j];
        cards[k] = rightHalfCards[j];
        j++;
      }
      k++;
    }
 
    while (i < leftSize) {
      inputArray[k] = leftHalf[i];
      cards[k] = leftHalfCards[i];
      i++;
      k++;
    }
 
    while (j < rightSize) {
      inputArray[k] = rightHalf[j];
      cards[k] = rightHalfCards[j];
      j++;
      k++;
    }
  }


    public static void main(String[] args) throws IOException {
      String[] cards = new String[100];
      int[] expiryDate = new int[100];
      
      
      try (BufferedReader reader = new BufferedReader(new FileReader("data.dat"))) {
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
          String[] fields = line.split(",");
          expiryDate[i] = Integer.parseInt(fields[5])*100 + Integer.parseInt(fields [4]);
          cards[i] = fields[0] +","+ fields[1] +","+ fields[2] +","+ fields[3];
          i++;
        }
        int count = 0;
        
        for (i = 0; expiryDate.length > i; i++){
          if (expiryDate[i] <= 202301) {
            count++;
          }
        }

        String[] cardsTemp = new String[count];
        int[] expiryDateTemp = new int[count];

        count = 0;

        for (i = 0; expiryDate.length > i; i++){
          if (expiryDate[i] <= 202301) {
            expiryDateTemp[count] = expiryDate[i];
            cardsTemp[count] = cards[i];
            count++;
          }
        }
        mergeSort(expiryDateTemp, cardsTemp);

        System.out.println("NAME                 Card Vendor     Card Number          Expiry   Status");

        String status;
        for (i = 0; expiryDateTemp.length > i; i++){
          String[] fields = cardsTemp[i].split(",");
          if (expiryDateTemp[i] == 202301) status = "RENEW IMMEDIATLY";
          else status = "EXPIRED";
          System.out.printf("%-20s %-15s %-20s %-8s %s\n", fields[0], fields[1], fields[2], expiryDateTemp[i], status);
        }
      } catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      

    }
}