package springbootrestapi.dto;

public class ResponseType {

	public static enum Response {

		SUCCESS(0, "Success"), FAILURE(1, "Failure"), INTERNAL_SERVER_ERROR(2, "Internal Server Error"), NOT_FOUND(3, "Not found"),
		INVALID_PARAMETERS(4, "Invalid parameter(s)"), BAD_REQUEST(5, "Bad request");

		private final int code;
		private final String text;

		Response(int code, String text) {
			this.code = code;
			this.text = text;
		}

		public int getCode() {
			return code;
		}

		public String getText() {
			return text;
		}

	}

	public static Response getResponse(int code) {
		Response[] resp = Response.values();
		for (int i = 0; i < resp.length; i++) {
			if (code == resp[i].getCode()) {
				return resp[i];
			}
		}
		return Response.INTERNAL_SERVER_ERROR;
	}

}