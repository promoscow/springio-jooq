select count(*)
from "public"."dishes"
         join "public"."restaurants" on "public"."restaurants"."id" = "public"."dishes"."restaurant_id"
         join "public"."orders" on "public"."orders"."restaurant_id" = "public"."restaurants"."id"
         join "public"."users" on "public"."users"."id" = "public"."orders"."user_id"
where "public"."dishes"."name" = 'Харчо с изюминкой';