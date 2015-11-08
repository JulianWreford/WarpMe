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
"type",
"value"
})

/**
 * @author Trip Admin
 *
 */
public class AttachedElement {

	@JsonProperty("type")
	private int type;
	@JsonProperty("value")
	private String value;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return
	 * The type
	 */
	@JsonProperty("type")
	public int getType() {
		return type;
	}

	/**
	 *
	 * @param type
	 * The type
	 */
	@JsonProperty("type")
	public void setType(int type) {
		this.type = type;
	}

	public AttachedElement withType(int type) {
		this.type = type;
		return this;
	}

	/**
	 *
	 * @return
	 * The value
	 */
	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	/**
	 *
	 * @param value
	 * The value
	 */
	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	public AttachedElement withValue(String value) {
		this.value = value;
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

public AttachedElement withAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	return this;
}
}
