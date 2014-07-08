public class Router {
    public void match(String route, String target) {
        System.out.println("Java route: " + route);
        System.out.println("Java target: " + target);
    }

    public void get(String route, String target) {
        System.out.println("Java get: " + route + " => " + target);
    }
}
