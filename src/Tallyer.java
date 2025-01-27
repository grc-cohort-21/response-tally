import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

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
        Map<String, Integer> myTopicCounts = new HashMap<>();
        for (String topic : topics)
        {
            if (myTopicCounts.containsKey(topic))
            {
                myTopicCounts.put(topic, myTopicCounts.get(topic)+1);
            }
            else
            {
                myTopicCounts.put(topic, 1);
            }
        }
        return myTopicCounts;
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
        System.out.println("");
        Map<String, Integer> theirVotesMap = new HashMap<>();
        List<String> validIds = new ArrayList<String>();
      
        //System.out.println("tallyTopics(id): " + tallyTopics(ids));

        for (String id : tallyTopics(ids).keySet())
        {
            if (tallyTopics(ids).get(id) == 2)
            {
                validIds.add(id);
            }
        }

        //System.out.println("ValidIds (list): " + validIds);
        //System.out.println("example output: {maps=1, loops=1, arrays=1}");

        Map<String, Integer> myMap = new HashMap<>(); // final map that will have the name of the votes and how many times
        int count = 0;
        for (int i=0; i < ids.size(); i++)
        {
            if (validIds.contains(ids.get(i)))
            {
                if (theirVotesMap.containsKey(ids.get(i)))
                {
                    theirVotesMap.put(ids.get(i), theirVotesMap.get(ids.get(i))+1);
                    myMap.put(topics.get(i), count+1); // myMap.put(topics.get(i), myMap.get(topics.get(i));
                }
                else
                {
                    theirVotesMap.put(ids.get(i), 1);
                    myMap.put(topics.get(i), 1);
                    count = 1;
                }
            }
        }
        //System.out.println(theirVotesMap);
        return myMap;
  }
}
