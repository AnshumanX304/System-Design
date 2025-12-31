from enum import Enum


class UserTier(str,Enum):
    FREE="free"
    PREMIUM="premium"

class RateLimiterType(str,Enum):
    LEAKY_BUCKET="leaky_bucket"
    TOKEN_BUCKET="token_bucket"
