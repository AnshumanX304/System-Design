from abc import ABC, abstractmethod
from dataclasses import dataclass
import datetime
from rate_limiter.models.rate_limiter_config import RateLimiterConfig, TokenBucketConfig, LeakyBucketConfig
from rate_limiter.enums.enums import RateLimiterType
from rate_limiter.models.users import User


@dataclass(slots=True)
class RateLimiter(ABC):
    rate_limiter_config: RateLimiterConfig
    rate_limiter_type: RateLimiterType

    @abstractmethod
    def allow_request(self, user: User) -> bool:
        pass


class LeakyBucketRateLimiter(RateLimiter):

    def __init__(
        self,
        rate_limiter_config: RateLimiterConfig,
        rate_limiter_type: RateLimiterType,
    ):
        super().__init__(
            rate_limiter_config=rate_limiter_config,
            rate_limiter_type=rate_limiter_type
        )
        self.leaky_bucket: dict[str, LeakyBucketConfig] = {}

    def allow_request(self, user: User) -> bool:
        now = datetime.datetime.now()
        user_id = user.user_id

        leak_rate = (
            self.rate_limiter_config.max_request
            / self.rate_limiter_config.window_in_sec
        )

        max_capacity = self.rate_limiter_config.max_request

        if user_id not in self.leaky_bucket:
            self.leaky_bucket[user_id] = LeakyBucketConfig(
                curr_capacity=1,
                last_leaked=now
            )
            return True

        bucket = self.leaky_bucket[user_id]

        elapsed = (now - bucket.last_leaked).total_seconds()
        leaked_water = elapsed * leak_rate

        bucket.curr_capacity = max(
            0,
            bucket.curr_capacity - leaked_water
        )
        bucket.last_leaked = now

        if bucket.curr_capacity + 1 > max_capacity:
            return False

        bucket.curr_capacity += 1
        return True



class TokenBucketRateLimiter(RateLimiter):
    def __init__(self, rate_limiter_config: RateLimiterConfig,rate_limiter_type: RateLimiterType,):
        super().__init__(rate_limiter_config=rate_limiter_config ,rate_limiter_type=rate_limiter_type)
        self.token_bucket: dict[str, TokenBucketConfig] = {}

    def allow_request(self, user: User) -> bool:
        now = datetime.datetime.now()
        user_id = user.user_id

        refill_rate = (
            self.rate_limiter_config.max_request
            / self.rate_limiter_config.window_in_sec
        )

        max_tokens = self.rate_limiter_config.max_request

        if user_id not in self.token_bucket:
            self.token_bucket[user_id] = TokenBucketConfig(
                token_cnt=max_tokens - 1,
                last_added=now
            )
            return True

        bucket = self.token_bucket[user_id]

        elapsed = (now - bucket.last_added).total_seconds()
        added_tokens = elapsed * refill_rate

        bucket.token_cnt = min(max_tokens, bucket.token_cnt + added_tokens)
        bucket.last_added = now

        if bucket.token_cnt < 1:
            return False

        bucket.token_cnt -= 1
        return True
