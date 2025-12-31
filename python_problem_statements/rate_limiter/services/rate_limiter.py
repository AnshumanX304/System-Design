from rate_limiter.models.rate_limiter import LeakyBucketRateLimiter,TokenBucketRateLimiter,RateLimiter
from rate_limiter.models.rate_limiter_config import RateLimiterConfig
from rate_limiter.enums.enums import RateLimiterType,UserTier
from rate_limiter.models.users import User

class RateLimiterService:
    def __init__(self, tier_to_rate_limiter: dict[UserTier, RateLimiter]):
        self.tier_to_rate_limiter = tier_to_rate_limiter

    def allow_request(self, user: User) -> bool:
        limiter = self.tier_to_rate_limiter.get(user.user_tier)
        if not limiter:
            raise ValueError(f"No rate limiter configured for {user.user_tier}")
        return limiter.allow_request(user)
    

class RateLimiterFactory:
    @staticmethod
    def create() -> dict[UserTier, RateLimiter]:
        return {
            UserTier.FREE: LeakyBucketRateLimiter(
                rate_limiter_config=RateLimiterConfig(
                    max_request=5,
                    window_in_sec=20
                ),
                rate_limiter_type=RateLimiterType.LEAKY_BUCKET
            ),
            UserTier.PREMIUM: TokenBucketRateLimiter(
                rate_limiter_config=RateLimiterConfig(
                    max_request=10,
                    window_in_sec=20
                ),
                rate_limiter_type=RateLimiterType.TOKEN_BUCKET
            ),
        }