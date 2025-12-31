from dataclasses import dataclass
from rate_limiter.enums.enums import UserTier

# class User:
#     def __init__(self,user_id:str, user_tier:UserTier) -> None:
#         self.user_id=user_id
#         self.user_tier=user_tier

#     def __eq__(self,other):
#         if not isinstance(other,User):
#             return NotImplemented
#         return self.user_id==other.user_id and self.user_tier==other.user_tier

#     def __repr__(self):
#         return f"User(user_id={self.user_id!r}, user_tier={self.user_tier!r})"

# no need to implement default __init__, __repr__ and __eq__ functions when using dataclass
@dataclass
class User:
    user_id: str
    user_tier: UserTier


# Without dataclass (and without overriding __eq__):
# User("123", UserTier.FREE) == User("123", UserTier.FREE) → False
# because object.__eq__ compares identity, not values.

# Without dataclass (and without overriding __repr__):
# print(User("123", UserTier.FREE)) shows a memory-address-based repr.

# Without dataclass:
# __init__, __eq__, and __repr__ must be implemented manually
# if value-based behavior is desired.

# @dataclass automatically generates __init__, __eq__, and __repr__,
# reducing boilerplate and making the class value-based by default.