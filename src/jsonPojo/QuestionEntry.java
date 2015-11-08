/**
 * 
 */
package jsonPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
"id",
"info",
"questionItem",
"options",
"answer",
"metaTags"
})

/**
 * @author Trip Admin
 *
 */
public class QuestionEntry {

	@JsonProperty("id")
	private int id;
	@JsonProperty("info")
	private Info info;
	@JsonProperty("questionItem")
	private QuestionItem questionItem;
	@JsonProperty("options")
	private List<Option> options = new ArrayList<Option>();
	@JsonProperty("answer")
	private Answer answer;
	@JsonProperty("metaTags")
	private List<String> metaTags = new ArrayList<String>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The id
	 */
	@JsonProperty("id")
	public int getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 * The id
	 */
	@JsonProperty("id")
	public void setId(int id) {
		this.id = id;
	}

	public QuestionEntry withId(int id) {
		this.id = id;
		return this;
	}

	/**
	 *
	 * @return
	 * The info
	 */
	@JsonProperty("info")
	public Info getInfo() {
		return info;
	}

	/**
	 *
	 * @param info
	 * The info
	 */
	@JsonProperty("info")
	public void setInfo(Info info) {
		this.info = info;
	}

	public QuestionEntry withInfo(Info info) {
		this.info = info;
		return this;
	}

	/**
	 *
	 * @return
	 * The questionItem
	 */
	@JsonProperty("questionItem")
	public QuestionItem getQuestionItem() {
		return questionItem;
	}

	/**
	 *
	 * @param questionItem
	 * The questionItem
	 */
	@JsonProperty("questionItem")
	public void setQuestionItem(QuestionItem questionItem) {
		this.questionItem = questionItem;
	}

	public QuestionEntry withQuestionItem(QuestionItem questionItem) {
		this.questionItem = questionItem;
		return this;
	}

	/**
	 *
	 * @return
	 * The options
	 */
	@JsonProperty("options")
	public List<Option> getOptions() {
		return options;
	}

	/**
	 *
	 * @param options
	 * The options
	 */
	@JsonProperty("options")
	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public QuestionEntry withOptions(List<Option> options) {
		this.options = options;
		return this;
	}

	/**
	 *
	 * @return
	 * The answer
	 */
	@JsonProperty("answer")
	public Answer getAnswer() {
		return answer;
	}

	/**
	 *
	 * @param answer
	 * The answer
	 */
	@JsonProperty("answer")
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public QuestionEntry withAnswer(Answer answer) {
		this.answer = answer;
		return this;
	}

	/**
	 *
	 * @return
	 * The metaTags
	 */
	@JsonProperty("metaTags")
	public List<String> getMetaTags() {
		return metaTags;
	}

	/**
	 *
	 * @param metaTags
	 * The metaTags
	 */
	@JsonProperty("metaTags")
	public void setMetaTags(List<String> metaTags) {
		this.metaTags = metaTags;
	}

	public QuestionEntry withMetaTags(List<String> metaTags) {
		this.metaTags = metaTags;
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

	public QuestionEntry withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
