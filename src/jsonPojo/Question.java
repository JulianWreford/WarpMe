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
"questionEntry"
})

/**
 * @author Trip Admin
 *
 */
public class Question {

	@JsonProperty("questionEntry")
	private QuestionEntry questionEntry;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The questionEntry
	 */
	@JsonProperty("questionEntry")
	public QuestionEntry getQuestionEntry() {
		return questionEntry;
	}

	/**
	 *
	 * @param questionEntry
	 * The questionEntry
	 */
	@JsonProperty("questionEntry")
	public void setQuestionEntry(QuestionEntry questionEntry) {
		this.questionEntry = questionEntry;
	}

	public Question withQuestionEntry(QuestionEntry questionEntry) {
		this.questionEntry = questionEntry;
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

	public Question withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
