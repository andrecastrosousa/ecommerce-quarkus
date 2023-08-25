package mindswap.academy.item.dto;

public class ReviewCreateDto {

    private double rating;

    private String commentary;

    private Long itemId;

    public ReviewCreateDto(double rating, String commentary, Long itemId) {
        this.rating = rating;
        this.commentary = commentary;
        this.itemId = itemId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
