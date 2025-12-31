import time
from rate_limiter.enums.enums import UserTier
from rate_limiter.models.users import User
from rate_limiter.services.rate_limiter import RateLimiterService, RateLimiterFactory


def build_rate_limiter_service() -> RateLimiterService:
    tier_to_rate_limiter = RateLimiterFactory.create()
    return RateLimiterService(tier_to_rate_limiter)


def test_user(service: RateLimiterService, user: User, requests: int, delay: float):
    print(f"\nTesting user: {user.user_id} ({user.user_tier.value})")
    for i in range(requests):
        allowed = service.allow_request(user)
        print(f"Request {i+1:02d}: {'ALLOWED' if allowed else 'BLOCKED'}")
        time.sleep(delay)


if __name__ == "__main__":
    service = build_rate_limiter_service()

    free_user = User(user_id="user_free_1", user_tier=UserTier.FREE)
    premium_user = User(user_id="user_premium_1", user_tier=UserTier.PREMIUM)

    test_user(
        service=service,
        user=free_user,
        requests=12,
        delay=0.5
    )

    test_user(
        service=service,
        user=premium_user,
        requests=15,
        delay=0.2
    )
