import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
    public static Map<String, Integer> tallyTopics(List<String> topics) {
        // WAVE 1
        // TODO: Implement this method
        
        // Creating a new HashMap since we need to return a map
        Map<String, Integer> tallyMap = new HashMap<>();

        //go through the list of topics 1 at a time
        for (String topic : topics){
            // if the map does not yet contain a topic
            if (!tallyMap.containsKey(topic)) {
                // add the map to the list with a count of 1
                tallyMap.put(topic, 1);
            } else { // if the topic is already in the map
                // retrieve and store the current count as topicCount
                int topicCount = tallyMap.get(topic);

                //increase the topic count by 1 (for the new item)
                topicCount = topicCount + 1;
                
                //put new number in the map
                tallyMap.put(topic,topicCount);
            }

        }
        // You didn't say if you wanted it sorted by keys or by value
        // TreeMap keys are automatically sorted, so we can put the HashMap in a TreeMap to sort by keys
        TreeMap<String, Integer> sortedTallyMap = new TreeMap<>(tallyMap);

        // return the map. Tallyer is not looking for a specific kind of map, so any kind is fine.
        return sortedTallyMap;
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

      // Setting up a list for "valid" topic entries
      List<String> validEntries = new ArrayList<>();

      // go through the list of ids one at a time
      for (int i = 0; i < ids.size(); i++) {
        
        /*
         I looked up collections.frequency() for this part
         https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#frequency(java.util.Collection,%20java.lang.Object).
         Collections.frequency will return the number of items in the given array (first parameter) which match the criteria (second parameter).
         By setting the second parameter to retrieve (ids.get()) the current value of the index we can automatically check how many items matching that index is in the array.

         With a little more work I think I could add a piece so we weren't re-checking IDs that we already know are invalid
         */ 
        int numberEntries = Collections.frequency(ids, ids.get(i));
        if (numberEntries == 2) {
            // we're looking for ids with two votes
            // if the ID is valid (2 votes) that should get added to our new list of valid topics
            validEntries.add(topics.get(i));
        }
      }
        // at the end of going through our list of ids, we should have a list of valid topics to be tallied
        // this can be passed to the method we already wrote and returned

      return tallyTopics(validEntries);
  }
}
