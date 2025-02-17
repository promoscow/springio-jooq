select "public"."restaurants"."id", "public"."restaurants"."name"
from "public"."restaurants"
         right outer join "public"."dishes" on "public"."dishes"."restaurant_id" = "public"."restaurants"."id"
         right outer join "public"."orders" on "public"."orders"."dish_id" = "public"."dishes"."id"
where "public"."orders"."user_id" = cast('1031f963-957c-425f-962e-767080a699cb' as uuid);