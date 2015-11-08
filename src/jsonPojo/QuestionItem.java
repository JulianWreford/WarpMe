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
"theQuestion",
"attachedElement"
})

/**
 * @author Trip Admin
 *
 */
public class QuestionItem {

	@JsonProperty("theQuestion")
	private String theQuestion;
	@JsonProperty("attachedElement")
	private AttachedElement attachedElement;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The theQuestion
	 */
	@JsonProperty("theQuestion")
	public String getTheQuestion() {
		return theQuestion;
	}

	/**
	 *
	 * @param theQuestion
	 * The theQuestion
	 */
	@JsonProperty("theQuestion")
	public void setTheQuestion(String theQuestion) {
		this.theQuestion = theQuestion;
	}

	public QuestionItem withTheQuestion(String theQuestion) {
		this.theQuestion = theQuestion;
		return this;
	}

	/**
	 *
	 * @return
	 * The attachedElement
	 */
	@JsonProperty("attachedElement")
	public AttachedElement getAttachedElement() {
		return attachedElement;
	}

	/**
	 *
	 * @param attachedElement
	 * The attachedElement
	 */
	@JsonProperty("attachedElement")
	public void setAttachedElement(AttachedElement attachedElement) {
		this.attachedElement = attachedElement;
	}

	public QuestionItem withAttachedElement(AttachedElement attachedElement) {
		this.attachedElement = attachedElement;
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

	public QuestionItem withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
