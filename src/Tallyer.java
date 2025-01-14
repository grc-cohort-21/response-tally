import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The Tallyer class provides functionality for reading ID and topic pairs from user input,
 * and tallying the number of occurrences of each topic.
 */
public class Tallyer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<String> ids = new ArrayList<>();
        List<String> topics = new ArrayList<>();

        // Reading input for IDs and topics
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
     *
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */
    public static Map<String, Integer> tallyTopics(List<String> topics) {
        Map<String, Integer> topicCounts = new HashMap<>();
        for (String topic : topics) {
            topicCounts.put(topic, topicCounts.getOrDefault(topic, 0) + 1);
        }
        return topicCounts;
    }

    /**
     * Tally the occurrences of valid votes for each topic from the provided lists of IDs and topics.
     *
     * The lists are of equal length and are aligned: the id at index zero cast a vote for
     * the topic at index 0, the id at index 1 cast a vote for the topic at index 1, etc.
     * It returns a map where each topic is associated with the number of times it appears in the input.
     * However, any user who did not enter exactly 2 topics should not have their votes counted.
     *
     * @param ids a list of strings representing IDs associated with each topic
     * @param topics a list of strings representing the topics to be tallied
     * @return a map containing topics as keys and their occurrence counts as values
     */
    public static Map<String, Integer> tallyTopicsFiltered(List<String> ids, List<String> topics) {
        Map<String, Integer> topicCounts = new HashMap<>();
        Map<String, Integer> userTopicCounts = new HashMap<>();

        // Count the number of topics per user
        for (String id : ids) {
            userTopicCounts.put(id, userTopicCounts.getOrDefault(id, 0) + 1);
        }

        // Tally topics only for users who voted exactly twice
        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            if (userTopicCounts.get(id) == 2) {
                String topic = topics.get(i);
                topicCounts.put(topic, topicCounts.getOrDefault(topic, 0) + 1);
            }
        }

        return topicCounts;
    }
}
