package Models;

public class Article {
  private String articleNbr;
  private String nbrOfItems;
  private String pricePerItem;
  private String totalPrice;

  public Article(String articleNbr, String nbrOfItems, String pricePerItem, String totalPrice) {
    this.articleNbr = articleNbr;
    this.nbrOfItems = nbrOfItems;
    this.pricePerItem = pricePerItem;
    this.totalPrice = totalPrice;
  }

  public String getArticleNbr() {
    return this.articleNbr;
  }

  public String getNbrOfItems() {
    return this.nbrOfItems;
  }

  public String getPricePerItem()
  {
    return this.pricePerItem;
  }

  public String getTotalPrice() {
    return this.totalPrice;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Article)) {
      return false;
    }
    Article that = (Article) other;
    return this.articleNbr.equals(that.articleNbr)
      && this.nbrOfItems.equals(that.nbrOfItems)
      && this.pricePerItem.equals(that.pricePerItem)
      && this.totalPrice.equals(that.totalPrice);
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 37 + this.articleNbr.hashCode();
    hashCode = hashCode * 37 + this.nbrOfItems.hashCode();
    hashCode = hashCode * 37 + this.pricePerItem.hashCode();
    hashCode = hashCode * 37 + this.totalPrice.hashCode();

    return hashCode;
  }

  @Override
  public String toString() {
    return "ArticleNbr: " + this.articleNbr
      + ", NbrOfItems: " + this.nbrOfItems
      + ", PricePerItem: " + this.pricePerItem
      + ", TotalPrice: " + this.totalPrice;
  }
}
