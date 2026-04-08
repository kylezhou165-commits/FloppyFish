public class test {
    public static void main(String[] args) {
        Flash f = new Flash();
        
        f.add("hi", "g");
        f.add("2", "g");
         f.add("3", "g");
          f.add("4", "g");
           f.add("5", "g");
        f.add("bye", "gg");
        
        f.nextCard();
        
        f.shuffle();
        
        System.out.println(f);

    }
}
