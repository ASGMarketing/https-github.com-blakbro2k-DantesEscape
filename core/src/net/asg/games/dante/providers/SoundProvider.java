package net.asg.games.dante.providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundProvider {

    private boolean isSoundOn;

    private Sound cannonSound;

    private Sound flameBurst;

    private Sound firewoosh;

    private Sound buzzSound;

    private Sound goalHitSound;

    private Sound deathSound;

    private Music bgStart;

    private Music bgLoop;

    private boolean isBGMLoopStarted = false;

    private boolean isPauseMusicOn = false;

    private boolean isBGMStartFinished = false;

    private Music pauseMusic;

    public SoundProvider() {

    }

    public void setSoundOn(boolean isSoundOn) {
        this.isSoundOn = isSoundOn;
    }

    public void load() {
        cannonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/cannon.ogg"));
        flameBurst = Gdx.audio.newSound(Gdx.files.internal("sounds/flameBurst2.ogg"));
        firewoosh = Gdx.audio.newSound(Gdx.files.internal("sounds/firewoosh.ogg"));
        buzzSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buzz.ogg"));
        goalHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/goalHit.ogg"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("sounds/death.ogg"));
        bgStart = Gdx.audio.newMusic(Gdx.files.internal("sounds/btoad-start.ogg"));
        bgLoop = Gdx.audio.newMusic(Gdx.files.internal("sounds/btoad-loop.ogg"));
        pauseMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/pause.ogg"));
        bgStart.setLooping(false);
        bgLoop.setLooping(true);
        pauseMusic.setLooping(true);
    }

    public void dispose() {
        cannonSound.dispose();
        flameBurst.dispose();
        firewoosh.dispose();
        buzzSound.dispose();
        goalHitSound.dispose();
        bgStart.dispose();
        bgLoop.dispose();
        deathSound.dispose();
        pauseMusic.dispose();
    }

    public void playCannonSound() {
        if (isSoundOn) {
            cannonSound.play();
        }
    }

    public void playflameBurstSound() {
        if (isSoundOn) {
            flameBurst.play();
        }
    }

    public void playfirewooshSound() {
        if (isSoundOn) {
            firewoosh.play();
        }
    }

    public void playBuzzSound() {
        if (isSoundOn) {
            long buzzSoundId = buzzSound.play();
            buzzSound.setVolume(buzzSoundId, 0.1f);
        }
    }

    public void playGoalHitSound() {
        if (isSoundOn) {
            goalHitSound.play();
        }
    }

    public void playDeathSound() {
        if (isSoundOn) {
            deathSound.play();
        }
    }

    public void playBgSound() {
        if (isSoundOn) {
            if (!isBGMLoopStarted) {
                bgStart.play();
                bgStart.setOnCompletionListener(new Music.OnCompletionListener() {
                    @Override
                    public void onCompletion(Music music) {
                        bgLoop.play();
                        isBGMStartFinished = true;
                    }
                });
                isBGMLoopStarted = true;
            }
        }
    }

    public void setBgMusicOff() {
        if (isSoundOn) {
            isSoundOn = false;
            if (isBGMLoopStarted) {
                bgLoop.pause();
            } else {
                bgStart.pause();
            }
        }
    }

    public void setBgMusicOn() {
        if (!isSoundOn) {
            isSoundOn = true;
            if (isBGMLoopStarted) {
                bgLoop.play();
            } else {
                bgStart.play();
            }
        }
    }

    public void setPauseMusicOn() {
        Gdx.app.log(this.toString(), "Pause Button Pressed");

        if (!isSoundOn) {
            if (!isPauseMusicOn && isBGMLoopStarted) {
                if (isBGMStartFinished)
                    bgLoop.pause();
                else
                    bgStart.pause();

                pauseMusic.play();
                isPauseMusicOn = true;
            }
        }
    }

    public void setPauseMusicOff() {
        Gdx.app.log(this.toString(), "Pause Button Pressed");

        if (isSoundOn) {
            if (isPauseMusicOn && isBGMLoopStarted) {
                pauseMusic.stop();
                if (isBGMStartFinished)
                    bgLoop.play();
                else
                    bgStart.play();
                isPauseMusicOn = false;
            }
        }
    }

    public void resetSoundBoard(){
        isBGMLoopStarted = false;
        isPauseMusicOn = false;
        isBGMStartFinished = false;
    }
    public void stopAllSounds(){
        if(bgStart.isPlaying())
            bgStart.stop();
        if(bgLoop.isPlaying())
            bgLoop.stop();
        if(pauseMusic.isPlaying())
            pauseMusic.stop();
            cannonSound.stop();
        flameBurst.stop();
        firewoosh.stop();
        buzzSound.stop();
        deathSound.stop();
    }
}
