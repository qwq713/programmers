package 해시;

import java.util.*;



class 베스트앨범 {
    static Map<String, Integer> genreCountMap = new HashMap<>();
    static Map<String, Integer> genreRankMap = new HashMap<>();

    class Genre implements Comparable<Genre> {
        String name;
        int count;

        public Genre(String name, int count) {
            this.name = name;
            this.count = count;
        }

        @Override
        public int compareTo(Genre other) {
            return other.count - this.count;
        }
    }

    class Music implements Comparable<Music> {

        int idx;
        String genre;
        int playCount;

        public Music(int idx, String genre, int playCount) {
            this.idx = idx;
            this.genre = genre;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Music other) {
            int thisRank = genreRankMap.get(this.genre);
            int otherRank = genreRankMap.get(other.genre);
            if (thisRank == otherRank) {
                if (this.playCount == other.playCount) {
                    return this.idx - other.idx;
                }
                return other.playCount - this.playCount;
            }
            return thisRank - otherRank;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Music> musicList = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            String genreName = genres[i];
            int playCount = plays[i];
            int genreCount = genreCountMap.getOrDefault(genreName, 0) + playCount;

            genreCountMap.put(genreName, genreCount);
            musicList.add(new Music(i, genreName, playCount));
        }

        // Genre의 Rank 구하기 위한 List
        List<Genre> genreList = new ArrayList<>();

        for (String genreName : genreCountMap.keySet()) {
            int count = genreCountMap.get(genreName);
            genreList.add(new Genre(genreName, count));
        }

        // Genre 정렬 이후 앞에서부터 Rank 부여
        Collections.sort(genreList);

        for (int i = 0; i < genreList.size(); i++) {
            genreRankMap.put(genreList.get(i).name, i);
        }

        // 1. Genre의 Rank
        // 2. Music의 PlayCount
        // 3. Music의 고유번호 ( idx ) 를 기준으로 정렬
        Collections.sort(musicList);


        // 각 장르별 2개 ( 최소 1개 ) 씩 선별하기 위해 answerList 생성
        List<Integer> answerList = new ArrayList<>();
        String nowGenreName = genreList.get(0).name;
        int count = 0;

        for (Music music : musicList) {
            if (music.genre.equals(nowGenreName) && count == 2) {
                continue;
            }
            if (music.genre.equals(nowGenreName) == false) {
                nowGenreName = music.genre;
                count = 0;
            }
            answerList.add(music.idx);
            count++;
        }

        // Java8 stream 중간 연산자 기능.
        int[] answer = answerList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}