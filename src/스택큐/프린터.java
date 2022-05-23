package 스택큐;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class 프린터 {

    class Document {
        int location;
        int priority;

        public Document(int idx, int priority) {
            this.location = idx;
            this.priority = priority;
        }
    }

    public int solution(int[] priorities, int location) {
        List<Document> documentList = new ArrayList<>();
        Deque<Document> documentDeque = new ArrayDeque<>();

        for (int i = 0; i < priorities.length; i++) {
            documentDeque.add(new Document(i, priorities[i]));
        }

        while (!documentDeque.isEmpty()) {
            int maxPriority = -1;
            for (Document document : documentDeque) {
                maxPriority = maxPriority < document.priority ? document.priority : maxPriority;
            }

            Document document = documentDeque.pollFirst();
            if (document.priority == maxPriority) {
                documentList.add(document);
            } else {
                documentDeque.add(document);
            }
        }

        int answer = 0;
        for (int i = 0; i < documentList.size(); i++) {
            if (documentList.get(i).location == location) {
                answer = i + 1;
            }
        }
        return answer;
    }
}
