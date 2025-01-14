import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

/**
 * The Tallyer class provides functionality for reading ID and topic pairs from user input,
 * and tallying the number of occurrences of each topic.
 */
public class Tallyer {

    /**
     * The main method serves as the entry point for the program. It reads pairs of IDs and topics
     * from standard input, stores them in lists, and then calculates the number of occurrences
     * of each topic. The IDs and topics are guaranteed to not include internal whitespace.
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<String> ids = new ArrayList<>();
        List<String> topics = new ArrayList<>();
        
        // Reading input for IDs and topics
        // Assumes file is well formed into pairs
        while (input.hasNext()) {
            ids.add(input.next());
            topics.add(input.next());
        }
        input.close();
        
        // Wave 1
        Map<String, Integer> topicCounts = tallyTopics(topics);
        //topicCounts.put(1 , lists);


        System.out.println("Here are how many times each topic appears (unfiltered):");
        System.out.println(topicCounts);

        // Wave 2
        Map<String, Integer> topicCountsFiltered = tallyTopicsFiltered(ids, topics);
        System.out.println("Here are how many times each topic appears (filtered):");
        System.out.println(topicCountsFiltered);
    }

    /**
     * Tally the occurrences of each topic from the provided list of topics.
     * This method takes a list of topics. It returns a Map where the keys are topics
     * and the values are how many times they appear in the input.
     *
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */
    public static Map<String, Integer> tallyTopics(List<String> topics) 
    {
        // WAVE 1
        // TODO: Implement this method
        Map<String, Integer> total = new HashMap<>();

        for(String topic:topics) //assigning topic as key
        {
            if (!total.containsKey(topic)) //if the total isn't in there
            {
            total.put(topic, 1); //put the total and assign it value 1
            } 
            else 
            {
                 int currentCount = total.get(topic); // temporary counter 
                 total.put(topic, currentCount + 1); //if it does
                 //contain total add 1 which turns integer to 2
             }
        }
        return total; //
    }

    /**
     * Tally the occurrences of valid votes for each topic from the provided lists of IDs and topics.
     * 
     * The lists are of equal length and are aligned: the id at index zero cast a vote for
     * the topic at endex 0, the id at index 1 cast a vote for the topic at index 1, etc.
     * It returns a map where each topic is associated with the number of times it appears in the input.
     * However, any user who did not enter exactly 2 topics should not have their votes counted.
     *
     * @param ids a list of strings representing IDs associated with each topic
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */
    public static Map<String, Integer> tallyTopicsFiltered(List<String> ids, List<String> topics) {
      // WAVE 2
      // TODO: Implement this method 
        Map<String, Integer> votes = new HashMap<>();
        Map<String, Integer> total = new HashMap<>();

        for(String ID:ids) //assigning key as ID 
        {
           if (!votes.containsKey(votes)) //if the votes isn't in there
            {
                votes.put(ID, 1); //put the votes and assign it value 1
                //System.out.println(votes);
            } 
            else 
            {
                 int currentCount = votes.get(ID); // temporary counter 
                 votes.put(ID, currentCount + 1); //if it does
                 //contain word add 1 which turns integer to 2
             }

             
        }

        for(int i =0; i<topics.size(); i++)
        {
            if(votes.get(ids.get(i)) == 2)
            {
                if(!total.containsKey(topics.get(i)))
                {
                    total.put(topics.get(i), 1);
                    System.out.println(total);
                }
                else 
                {
                    int currentCount = total.get(topics.get(i));
                    int updatedCount = currentCount + 1;
                    total.put(topics.get(i), updatedCount);
                    System.out.println(total);
                }
            }
        }

      return total; 
  }
}


// java -cp src Tallyer < responses.txt  // when you run this it is telling the code to read the responses.txt
// javac src/Tallyer.java // compile code default