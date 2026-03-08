package koroler.TThotels.params;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Параметр группировки для гистограммы")
public enum GroupingFactor {
	brand,
	city,
	country,
	amenities
}
