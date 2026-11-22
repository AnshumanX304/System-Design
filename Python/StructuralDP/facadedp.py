class MusicPlayer():
    def initialiseAudioDriver(self):
        print("The audio drivers are initialised !")

    def decodeAudio(self):
        print("The audio is being decoded !")

    def startPlayback(self):
        print("The audio playback is started !")

class VideoPlayer():
    def setupRenderingEngine(self):
        print("The video drivers are initialised !")
    
    def loadAudio(self):
        print("The video is being loaded !")

    def playVideo(self):
        print("The video play is started !")

class MediaFacade():

    def __init__(self):
        self.__videoplayer=VideoPlayer()
        self.__audioplayer=MusicPlayer()

    def performAction(self,action:str):

        if action=="playmusic":
            self.__audioplayer.initialiseAudioDriver()
            self.__audioplayer.decodeAudio()
            self.__audioplayer.startPlayback()

        elif action=="playvideo":
            self.__videoplayer.setupRenderingEngine()
            self.__videoplayer.loadAudio()
            self.__videoplayer.playVideo()
        else:
            print("Unsupported action !")


if __name__=="__main__":
    mediafacade=MediaFacade()
    action="playmusic"
    mediafacade.performAction(action=action)
    action="playvideo"
    mediafacade.performAction(action=action)



# "Facade Pattern provides a simplified interface to hide the complexity of multiple 
# underlying subsystems."