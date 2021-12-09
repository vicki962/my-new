import java.io.*;
import java.util.*;

class Main {

  public static void main(String[] args) throws FileNotFoundException
  {
    boolean VERBOSE = false; // convert it to true to get each string output
    Map<String,Integer> freq = new ChainHashMap<>();  // or any concrete map
    // scan input for words, using all nonletters as delimiters
    int Icapacity = freq.getCapacity();
    Scanner doc = new Scanner(new File("TaleOfTwoCities.txt"));
    while (doc.hasNext()) {
      String word = doc.next().toLowerCase();   // convert next word to lowercase
      if (VERBOSE)
        System.out.print("word = [" + word + "]");
      Integer count = freq.get(word);                  // get the previous count for this word
      if (count == null)
        count = 0;                                     // if not in map, previous count is zero
      freq.put(word, 1 + count);                       // (re)assign new count for this word
      if (VERBOSE)
        System.out.println(", count = " + freq.get(word));
    }
    int maxCount = 0;
    String maxWord = "no word";
    for (Entry<String,Integer> ent : freq.entrySet())    // find max-count word
      if (ent.getValue() > maxCount) {
        maxWord = ent.getKey();
        maxCount = ent.getValue();
      }
    System.out.print("The most frequent word is '" + maxWord);
    System.out.println("' with " + maxCount + " occurrences.");

    //Task2
    Map<String, Queue<Integer>> freq1 = new ChainHashMap<>();
    Scanner doc1 = new Scanner(new File("TaleOfTwoCities.txt"));
    int count = 0;
    while(doc1.hasNext())
    {
      String word1 = doc1.next().toLowerCase();

      if(freq1.get(word1) == null)
      {
        Queue<Integer> queue = new LinkedList<Integer>();
        freq1.put(word1, queue);
        freq1.get(word1).add(count++);
      }
      else
      {
        freq1.get(word1).remove(); 
        freq1.get(word1).add(count++);
      }
    }

    String a = "" + freq1.get("the") + ""; //to remove brackets around value
    String b = "" + freq1.get("known") + ""; //removed in the next lines

    System.out.println("The last occurrence of 'the' is at location " + a.substring(1, 7));
    System.out.println("The last occurrence of 'known' is at location " + b.substring(1, 7));

    //Task3
    int sum = 0;
    for(Entry<String,Integer> c : freq.entrySet())
      sum = sum + c.getValue();
    System.out.println("The number of words is: " + sum);

    //Task5
    //Icapacity is defined earlier
    int Fcapacity = freq.getCapacity();
    System.out.println("The hashtable has an original capacity of: " + Icapacity + " and a final capacity of: " + Fcapacity);

    //Task6
    int NumberOfEntries = 0;
    for(Entry<String,Integer> entry : freq.entrySet())
      NumberOfEntries++;
    float load = (float) Fcapacity / NumberOfEntries;
    System.out.println("The hashtable has a load factor of: " + load);

    //Task7
    String[] keys = new String[5]; //Stores keys
    int[] values = new int[5]; //Stores values

    String maxWord1 = "no word";
    int maxCount1 = 0;

    values[0] = 8052; //Knowing that highest value is 8051, set first value as 8052

    for(int i = 0; i < 5; i++)
    {
      for(Entry<String,Integer> entry : freq.entrySet())
      {
        if(i == 0)
        {
          if(entry.getValue() > maxCount1)
          {
            maxWord1 = entry.getKey();
            maxCount1 = entry.getValue();
          }
        }
        else
        {
          if(entry.getValue() > maxCount1 && entry.getValue() < values[i-1])
          {
            maxWord1 = entry.getKey();
            maxCount1 = entry.getValue();
          }
        }
      }
      keys[i] = maxWord1;
      values[i] = maxCount1;
      maxWord1 = "no word";
      maxCount1 = 0;
    }

    System.out.print("The most frequent words are: ");
    for(int i = 0; i < 5; i++)
    {
      if(i == 4)
        System.out.print("and '" + keys[i] + "'" + " with " + values[i] + " occurences. ");
      else
        System.out.print("'" + keys[i] + "'" + " with " + values[i] + " occurences, ");
    }

    //Task8
    //Go through all keys and see if the hash code.... ?

    //Bonus
    
  }
}