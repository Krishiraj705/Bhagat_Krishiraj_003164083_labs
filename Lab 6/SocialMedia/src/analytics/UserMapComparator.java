package analytics;

import java.util.Comparator;
import java.util.Map;
import model.User;

/**
 * Sorts users by a number stored for each user id (post count, comment count, ...).
 * ascending = true puts the least active users first; false puts the most active first.
 * Users with the same number are sorted by id so the order is always the same.
 *
 * @author Krishiraj Bhagat (Lab 6)
 */
public class UserMapComparator implements Comparator<User> {

    private final Map<Integer, Integer> userValues;
    private final boolean ascending;

    public UserMapComparator(Map<Integer, Integer> userValues, boolean ascending) {
        this.userValues = userValues;
        this.ascending = ascending;
    }

    private int valueOf(User u) {
        Integer v = userValues.get(u.getId());
        return v == null ? 0 : v;
    }

    @Override
    public int compare(User u1, User u2) {
        int result = Integer.compare(valueOf(u1), valueOf(u2));
        if (!ascending) {
            result = -result;
        }
        if (result == 0) {
            result = Integer.compare(u1.getId(), u2.getId());
        }
        return result;
    }
}
