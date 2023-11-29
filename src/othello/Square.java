package othello;

public class Square {
    private String owner; // "light", "dark", or "empty"

    public Square() {
        this.owner = "empty";
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    // Check if the square is empty
    public boolean isEmpty() {
        return "empty".equals(owner);
    }

    // Flip the counter's color
    public void flip() {
        if (!isEmpty()) {
            owner = "light".equals(owner) ? "dark" : "light";
        }
    }

    // For debugging and display purposes
    @Override
    public String toString() {
        // Returns "L", "D", or "E"
        return owner.equals("empty") ? "." : owner.substring(0, 1).toUpperCase();
    }
}
