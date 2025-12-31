from dataclasses import dataclass
import datetime


@dataclass
class RateLimiterConfig:
    max_request:int
    window_in_sec:int

@dataclass
class TokenBucketConfig:
    token_cnt: float
    last_added: datetime.datetime

@dataclass
class LeakyBucketConfig:
    curr_capacity: int
    last_leaked: datetime.datetime