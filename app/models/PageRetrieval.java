package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.avaje.ebean.Ebean;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class PageRetrieval extends Model {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long primaryKey;
  @Required
  private Long timestamp = System.currentTimeMillis();

  /**
   * The EBean ORM finder method for database queries on LastTimeStamp.
   * @return The finder method for products.
   */
  public static Finder<Long, PageRetrieval> find() {
    return new Finder<Long, PageRetrieval>(Long.class, PageRetrieval.class);
  }

  /**
   * @return the timestamp associated with the page retrieval.
   */
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * @param timestamp the timestamp to set
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

}
