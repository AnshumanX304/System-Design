
class Logger:

    # ==========================================
    # CLASS VARIABLE
    # ==========================================

    # Class variables belong to the class itself,
    # not to individual objects.
    #
    # They are shared across all instances of Logger.
    #
    # We declare this outside __init__ because we
    # need to access it even before an object exists.
    #
    # Initially None means no Logger instance exists.
    #
    # A single underscore indicates that this variable
    # is intended for internal use (not strictly private).
    _logger = None


    # ==========================================
    # OBJECT CREATION: __new__()
    # ==========================================

    # __new__() is a special method responsible for
    # creating and returning an object.
    #
    # Unlike normal instance methods, it receives cls
    # as its first argument.
    #
    # cls refers to the class itself (Logger),
    # whereas self refers to an individual object.
    #
    # Every time we call Logger(), Python invokes
    # __new__() before __init__().
    def __new__(cls):

        # Check whether a Logger instance already exists.
        #
        # cls._logger accesses the class variable through
        # the class (cls), not through an object (self).
        if cls._logger is None:

            # super() provides access to the next implementation
            # in the Method Resolution Order (MRO).
            #
            # Since Logger inherits from object by default,
            # super().__new__(cls) effectively calls:
            #
            # object.__new__(cls)
            #
            # This delegates actual object creation to Python's
            # built-in implementation.
            #
            # We override __new__() to control object creation,
            # but reuse the original implementation to create
            # the actual Logger object.
            #
            # The newly created object is stored in the
            # class variable _logger.
            cls._logger = super().__new__(cls)

        # Return the existing instance if one already exists.
        #
        # First Logger() call:
        # Creates a new object and returns it.
        #
        # Subsequent Logger() calls:
        # Returns the previously created object.
        #
        # This is how the Singleton pattern ensures
        # that only one instance is used.
        return cls._logger


    # ==========================================
    # OBJECT INITIALIZATION: __init__()
    # ==========================================

    # __init__() initializes an already-created object.
    #
    # Unlike __new__(), it receives self as its first argument.
    # self refers to the current Logger instance.
    #
    # Important: Python calls __init__() every time Logger()
    # is called if __new__() returns an instance of Logger.
    def __init__(self):

        # Instance variable.
        #
        # Instance variables belong to individual objects
        # and are generally declared using self inside __init__().
        #
        # Unlike class variables, every object can have
        # its own instance attributes.
        #
        # Since this is a Singleton, repeated calls to Logger()
        # will initialize the same object again.
        self.initialized = True


    # ==========================================
    # INSTANCE METHOD
    # ==========================================

    # A normal instance method receives self automatically.
    #
    # self refers to the object on which the method is called.
    #
    # msg: str is a type annotation indicating that msg
    # is expected to be a string.
    #
    # -> None indicates that this method is not expected
    # to return a meaningful value.
    def log_message(self, msg: str) -> None:

        # f-string allows us to insert variables directly
        # inside a string using curly braces.
        print(f"Hello {msg} !")


# ==========================================
# MAIN EXECUTION
# ==========================================

# __name__ is a special built-in Python variable.
#
# If this file is executed directly:
#     __name__ = "__main__"
#
# If this file is imported into another Python file:
#     __name__ = the module's name
#
# This condition ensures that the following code
# executes only when this file is run directly.
if __name__ == "__main__":

    # --------------------------------------
    # FIRST OBJECT CREATION
    # --------------------------------------

    # Logger() requests a Logger object.
    #
    # Internally, Python approximately performs:
    #
    # 1. Logger.__new__(Logger)
    # 2. __new__() checks whether _logger is None.
    # 3. Since it is None, a new object is created.
    # 4. The object is stored in Logger._logger.
    # 5. The object is returned.
    # 6. Python calls __init__() on that object.
    #
    # logger now references the newly created object.
    logger = Logger()


    # --------------------------------------
    # CALLING AN INSTANCE METHOD
    # --------------------------------------

    # Calls the log_message() method.
    #
    # Python automatically passes logger as self.
    #
    # Equivalent to:
    # Logger.log_message(logger, "Simon, Go Back !!")
    logger.log_message("Simon, Go Back !!")


    # --------------------------------------
    # SECOND OBJECT CREATION ATTEMPT
    # --------------------------------------

    # Another attempt to create a Logger object.
    #
    # Python again calls __new__(Logger).
    #
    # This time _logger is NOT None.
    # Therefore, __new__() returns the existing object.
    #
    # Python then calls __init__() AGAIN on that same object.
    #
    # logger2 now references exactly the same object as logger.
    logger2 = Logger()


    # --------------------------------------
    # CHECKING OBJECT IDENTITY
    # --------------------------------------

    # 'is' checks whether two variables reference
    # exactly the same object in memory.
    #
    # Since both variables reference the same Singleton
    # instance, the result is True.
    print(logger is logger2)


    # Both variables refer to the same object,
    # so their instance attributes are also shared.
    print(logger.initialized)
    print(logger2.initialized)
