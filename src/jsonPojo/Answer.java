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
"questionId",
"answer"
})



/**
 * @author Trip Admin
 *
 */
public class Answer {

	@JsonProperty("questionId")
	private int questionId;
	@JsonProperty("answer")
	private int answer;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The questionId
	 */
	@JsonProperty("questionId")
	public int getQuestionId() {
		return questionId;
	}

	/**
	 *
	 * @param questionId
	 * The questionId
	 */
	@JsonProperty("questionId")
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public Answer withQuestionId(int questionId) {
		this.questionId = questionId;
		return this;
	}

	/**
	 *
	 * @return
	 * The answer
	 */
	@JsonProperty("answer")
	public int getAnswer() {
		return answer;
	}

	/**
	 *
	 * @param answer
	 * The answer
	 */
	@JsonProperty("answer")
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public Answer withAnswer(int answer) {
		this.answer = answer;
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

	public Answer withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
