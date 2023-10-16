import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        //first input of the line will give the number of operations for the teque
        int operations = io.getInt();
        StringBuilder finalAnswer = new StringBuilder();
        Teque newTeque = new Teque();
        for (int i = 0; i < operations; i++) {
            String function = io.getWord();
            int number = io.getInt();
            //split into function and number
            if (function.equals("push_back")) {
                newTeque.push_back(number);
            } else if (function.equals("push_front")) {
                newTeque.push_front(number);
            } else if (function.equals("push_middle")) {
                newTeque.push_middle(number);
            } else if (function.equals("get")) {
                finalAnswer.append(newTeque.get(number));
                finalAnswer.append("\n");
            }
        }

        io.println(finalAnswer);
        io.close();
    }
}
class Teque {
    //we want to break the Teque into two different Hashmaps
    //there will be HashMap that stores the front and HashMap that stores the Back
    //each of these HashMaps will have a starting index of 0 FrontStartIndex / BackStartIndex
    //for any values that are pushed to the front, the index will be FrontStartIndex - 1
    //as we are adding to the "front". We will call this index the FrontTravellingIndex
    //for any values that are pushed to the back, the index will be BackStartIndex + 1
    //as we are adding to the "back". We will call this index the BackTravellingIndex
    //all indexes are inititialised at 0
    int frontHeadPointer = 0;
    int frontTailPointer = 1;
    int backHeadPointer = 0;
    int backTailPointer = 1;

    //declaring two hashmaps
    HashMap<Integer, Integer> front = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> back = new HashMap<Integer, Integer>();

    //We will support 5 methods
    //push_front
    //push_back
    //push_middle
    //balance
    //get
    //all these methods must be in O(1) time
    //to ensure that we are always slotting middle into the right index, we must balance the size of the hashMap

    //REMOVE TAIL MEANS DECREMENT TAILPOINTER
    // ADD TO HEAD MEANS DECREMENT HEADPOINTER
    //REMOVE HEAD MEANS INCREMENT HEADPOINTER
    // ADD TO TAIL MEANS INCREMENT TAILPOINTER

    void push_front(int value) {
        //we want to push the value into the front therefore the new frontHeadPointer must be one before the old frontHeadPointer. Thus
        //frontHeadPointer--
        front.put(frontHeadPointer, value);
        frontHeadPointer--;
        if (front.size() > back.size() + 1) {
            //if the front hashMap is larger than the back hashMap by more than 1 element, we need to resize it
            //we do so by removing the last element of front hashMap at frontTailPointer - 1 index and add it to the head of back hashMap
            back.put(backHeadPointer, front.remove(frontTailPointer - 1));
            //after doing so, we must decrement the front tail Pointer by 1 since we removed the last element from front hashMap
            //and the backHeadPointer must also decrement by 1 since we added the element to the head of back hashMap
            frontTailPointer--;
            backHeadPointer--;
        }
        else if (back.size() > front.size()) {
            //if back hashMap size is larger than front hashMap size
            //we will add the first element of back hashMap which is at index backHeadPointer + 1 to the last element of front hashMap;
            front.put(frontTailPointer, back.remove(backHeadPointer + 1));
            //after doing so, we need to increment the backHeadPointer by 1 since we removed head (REMOVE HEAD MEANS INCREMENT HEADPOINTER)
            //we also need to increment the frontTailPointer by 1 since we added to tail (ADD TO TAIL MEANS INCREMENT TAILPOINTER)
            backHeadPointer++;
            frontTailPointer++;
        }
    }

    void push_back(int value) {
        //we want to push the value to the back therefore the new backTailPointer must be one after the old backTailPointer. Thus
        //backTailPointer++
        back.put(backTailPointer, value);
        backTailPointer++;
        if (front.size() > back.size() + 1) {
            //if the front hashMap is larger than the back hashMap by more than 1 element, we need to resize it
            //we do so by removing the last element of front hashMap at frontTailPointer - 1 index and add it to the head of back hashMap
            back.put(backHeadPointer, front.remove(frontTailPointer - 1));
            //after doing so, we must decrement the front tail Pointer by 1 since we removed the last element from front hashMap
            //and the backHeadPointer must also decrement by 1 since we added the element to the head of back hashMap
            frontTailPointer--;
            backHeadPointer--;
        }
        else if (back.size() > front.size()) {
            //if back hashMap size is larger than front hashMap size
            //we will add the first element of back hashMap which is at index backHeadPointer + 1 to the last element of front hashMap;
            front.put(frontTailPointer, back.remove(backHeadPointer + 1));
            //after doing so, we need to increment the backHeadPointer by 1 since we removed head (REMOVE HEAD MEANS INCREMENT HEADPOINTER)
            //we also need to increment the frontTailPointer by 1 since we added to tail (ADD TO TAIL MEANS INCREMENT TAILPOINTER)
            backHeadPointer++;
            frontTailPointer++;
        }
    }

    void push_middle(int value) {
        //balance will settle the indexing for middle
        //hence we just need to add it to the tail of front hashMap
        front.put(frontTailPointer, value);
        frontTailPointer++;
        if (front.size() > back.size() + 1) {
            //if the front hashMap is larger than the back hashMap by more than 1 element, we need to resize it
            //we do so by removing the last element of front hashMap at frontTailPointer - 1 index and add it to the head of back hashMap
            back.put(backHeadPointer, front.remove(frontTailPointer - 1));
            //after doing so, we must decrement the front tail Pointer by 1 since we removed the last element from front hashMap
            //and the backHeadPointer must also decrement by 1 since we added the element to the head of back hashMap
            frontTailPointer--;
            backHeadPointer--;
        }
        else if (back.size() > front.size()) {
            //if back hashMap size is larger than front hashMap size
            //we will add the first element of back hashMap which is at index backHeadPointer + 1 to the last element of front hashMap;
            front.put(frontTailPointer, back.remove(backHeadPointer + 1));
            //after doing so, we need to increment the backHeadPointer by 1 since we removed head (REMOVE HEAD MEANS INCREMENT HEADPOINTER)
            //we also need to increment the frontTailPointer by 1 since we added to tail (ADD TO TAIL MEANS INCREMENT TAILPOINTER)
            backHeadPointer++;
            frontTailPointer++;
        }
    }


    int get(int index) {
        //if the index is within the size of the front hashMap, we can just directly get it after offsetting for
        //0 based indexing and adding index to the headPointer
        if (index < front.size()){
            return front.get(frontHeadPointer + index + 1);
        }
        else {
            //we need to look through the back hashMap to find our value
            int remainderToSearch = ((index + 1) - front.size());
            return back.get(remainderToSearch + backHeadPointer);
        }
    }


}
class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}


