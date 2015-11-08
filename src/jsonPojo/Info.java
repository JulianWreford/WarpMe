/**
 * 
 */
package jsonPojo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"seasonNumber",
"episodeNumber",
"episodeName"
})

/**
 * @author Trip Admin
 *
 */
public class Info {

	@JsonProperty("seasonNumber")
	private int seasonNumber;
	@JsonProperty("episodeNumber")
	private int episodeNumber;
	@JsonProperty("episodeName")
	private String episodeName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The seasonNumber
	 */
	@JsonProperty("seasonNumber")
	public int getSeasonNumber() {
		return seasonNumber;
	}

	/**
	 *
	 * @param seasonNumber
	 * The seasonNumber
	 */
	@JsonProperty("seasonNumber")
	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public Info withSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
		return this;
	}

	/**
	 *
	 * @return
	 * The episodeNumber
	 */
	@JsonProperty("episodeNumber")
	public int getEpisodeNumber() {
		return episodeNumber;
	}

	/**
	 *
	 * @param episodeNumber
	 * The episodeNumber
	 */
	@JsonProperty("episodeNumber")
	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public Info withEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
		return this;
	}

	/**
	 *
	 * @return
	 * The episodeName
	 */
	@JsonProperty("episodeName")
	public String getEpisodeName() {
		return episodeName;
	}

	/**
	 *
	 * @param episodeName
	 * The episodeName
	 */
	@JsonProperty("episodeName")
	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public Info withEpisodeName(String episodeName) {
		this.episodeName = episodeName;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Info withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
