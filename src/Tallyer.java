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
        Map<String, Integer> tallys = new HashMap<String, Integer>();
        for(String topic : topics) {
            if(!tallys.containsKey(topic)){
                tallys.put(topic, 1);
            }
            else{
                int currentCount = tallys.get(topic);
                int newCount = currentCount + 1;
                tallys.put(topic, newCount);
            }
        }

        return tallys;
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
      Map<String, Integer> tallys = new HashMap<String, Integer>();
      Map<String, Integer> numIds = new HashMap<String, Integer>();
      for (String id : ids) {
        if(!numIds.containsKey(id)){
            numIds.put(id, 1);
        }
        else{
            int currentCount = numIds.get(id);
            int newCount = currentCount + 1;
            numIds.put(id, newCount);
        }
      }
      List<String> newTopics = new ArrayList<String>();
      Map<String, Integer> idRepeat = new HashMap<String, Integer>();
      for (String id2 : ids) {
        if (numIds.get(id2) == 2 && !idRepeat.containsKey(id2)) {
            for (int i = 0; i < ids.size(); i++) {
                if (ids.get(i).equals(id2)) {
                    newTopics.add(topics.get(i));
                }
            }
            idRepeat.put(id2, 1);
        }
      }
      //System.out.println(newTopics);
      for(String topic : newTopics) {
        if(!tallys.containsKey(topic)){
            tallys.put(topic, 1);
        }
        else{
            int currentCount = tallys.get(topic);
            int newCount = currentCount + 1;
            tallys.put(topic, newCount);
        }
    }

      return tallys;
  }
}
